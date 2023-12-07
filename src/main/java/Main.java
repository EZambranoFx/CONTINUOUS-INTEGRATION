import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Book {
    String title;
    String author;
    int quantity;
    int available;

    public Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.available = quantity;
    }
}

class Library {
    ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
        // Sample books in the library
        books.add(new Book("Book1", "Author1", 5));
        books.add(new Book("Book2", "Author2", 8));
        books.add(new Book("Book3", "Author3", 3));
    }

    public void displayCatalog() {
        System.out.println("Catalog:");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println((i + 1) + ". " + book.title + " by " + book.author + " - Available: " + book.available);
        }
    }

    public double checkoutBooks(Scanner scanner) {
        ArrayList<Book> selectedBooks = new ArrayList<>();
        double totalLateFee = 0;

        while (true) {
            displayCatalog();
            try {
                System.out.print("Enter the number of the book to checkout (0 to finish): ");
                int bookIndex = Integer.parseInt(scanner.nextLine()) - 1;

                if (bookIndex == -1) {
                    break;
                }

                Book selectedBook = books.get(bookIndex);

                System.out.print("How many copies of '" + selectedBook.title + "' would you like to borrow? ");
                int quantity = Integer.parseInt(scanner.nextLine());

                if (quantity <= 0 || quantity > selectedBook.available || quantity > 10) {
                    System.out.println("Invalid quantity. Please try again.");
                    continue;
                }

                selectedBooks.add(selectedBook);
                selectedBook.available -= quantity;

            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }

        // Calculate due dates and late fees
        for (Book book : selectedBooks) {
            Date dueDate = new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000);
            double lateFee = Math.max(0, (dueDate.getTime() - System.currentTimeMillis()) / (24 * 60 * 60 * 1000)) * 1;
            totalLateFee += lateFee;

            System.out.println("\nCheckout Summary:");
            System.out.println("Book: " + book.title + " by " + book.author);
            System.out.println("Quantity: " + book.quantity);
            System.out.println("Due Date: " + dueDate);
            System.out.println("Late Fee: $" + lateFee);
        }

        System.out.println("\nTotal Late Fees: $" + totalLateFee);
        return totalLateFee;
    }

    public double returnBooks(Scanner scanner) {
        double totalLateFee = 0;

        while (true) {
            System.out.print("Enter the title of the book to return (or '0' to finish): ");
            String title = scanner.nextLine();

            if (title.equals("0")) {
                break;
            }

            boolean found = false;
            for (Book book : books) {
                if (book.title.equals(title)) {
                    found = true;
                    System.out.print("How many copies of '" + book.title + "' are you returning? ");
                    int returnedQuantity = Integer.parseInt(scanner.nextLine());

                    if (returnedQuantity <= 0 || returnedQuantity > 10) {
                        System.out.println("Invalid quantity. Please try again.");
                        continue;
                    }

                    book.available += returnedQuantity;
                    double lateFee = Math.max(0, (System.currentTimeMillis() - 14 * 24 * 60 * 60 * 1000) / (24 * 60 * 60 * 1000)) * 1;
                    totalLateFee += lateFee;

                    System.out.println("\nReturn Summary:");
                    System.out.println("Book: " + book.title + " by " + book.author);
                    System.out.println("Returned Quantity: " + returnedQuantity);
                    System.out.println("Late Fee for returned book: $" + lateFee);
                }
            }

            if (!found) {
                System.out.println("Book '" + title + "' not found in the catalog. Please try again.");
            }
        }

        System.out.println("\nTotal Late Fees for all returned books: $" + totalLateFee);
        return totalLateFee;
    }
}

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Library library = new Library();

            while (true) {
                System.out.println("\nLibrary Book Checkout System");
                System.out.println("1. Checkout Books");
                System.out.println("2. Return Books");
                System.out.println("3. Exit");

                try {
                    System.out.print("Enter your choice (1/2/3): ");
                    String choice = scanner.nextLine();

                    switch (choice) {
                        case "1":
                            library.checkoutBooks(scanner);
                            break;
                        case "2":
                            library.returnBooks(scanner);
                            break;
                        case "3":
                            System.out.println("Exiting the program. Thank you!");
                            return; // Salir del método main, finalizando el programa
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                    break; // Salir del bucle si se produce otra excepción
                }
            }
        }
    }
}
