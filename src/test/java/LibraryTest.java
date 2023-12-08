import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class LibraryTest {

    @Test
    public void testCheckoutBooks() {
        Library library = new Library();

        // Libros de muestra
        library.books.add(new Book("Book1", "Author1", 5));
        library.books.add(new Book("Book2", "Author2", 8));
        library.books.add(new Book("Book3", "Author3", 3));

        // Simular la entrada del usuario
        List<Integer> checkoutBookIndices = new ArrayList<>();
        checkoutBookIndices.add(1);
        checkoutBookIndices.add(2);
        checkoutBookIndices.add(2);
        checkoutBookIndices.add(0);

        List<Integer> checkoutQuantities = new ArrayList<>();
        checkoutQuantities.add(2);
        checkoutQuantities.add(2);
        checkoutQuantities.add(0);

        double lateFee = library.checkoutBooks(checkoutBookIndices, checkoutQuantities);
        
        assertEquals(28, lateFee); // No debería haber tarifas de demora simuladas en este caso
        assertEquals(3, library.books.get(0).available); // La cantidad disponible debe reducirse después del check-out
    }

    @Test
    public void testReturnBooks() {
        Library library = new Library();

        // Agregar algunos libros de muestra
        library.books.add(new Book("Book1", "Author1", 5));
        library.books.add(new Book("Book2", "Author2", 8));
        library.books.add(new Book("Book3", "Author3", 3));

        // Simular la entrada del usuario
        List<String> returnBookTitles = new ArrayList<>();
        returnBookTitles.add("Book1");
        returnBookTitles.add("2");
        returnBookTitles.add("0");

        List<Integer> returnQuantities = new ArrayList<>();
        returnQuantities.add(2);
        returnQuantities.add(0);

        double lateFee = library.returnBooks(returnBookTitles, returnQuantities);
        
        assertEquals(39370, lateFee); // No debería haber tarifas de demora simuladas en este caso
        assertEquals(7, library.books.get(0).available); // La cantidad disponible debe aumentar después del retorno
    }
}

