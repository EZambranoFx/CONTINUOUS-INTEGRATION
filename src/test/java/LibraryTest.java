import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;

public class LibraryTest {

    private final ByteArrayInputStream originalSystemIn = (ByteArrayInputStream) System.in;

    @BeforeEach
    public void setUp() {
        // Guardar System.in original antes de cada prueba
        System.setIn(new ByteArrayInputStream("".getBytes()));
    }

    @AfterEach
    public void tearDown() {
        // Restaurar System.in después de cada prueba
        System.setIn(originalSystemIn);
    }

    @Test
    public void testCheckoutBooks() {
        Library library = new Library();

        // Libros de muestra
        library.books.add(new Book("Book1", "Author1", 5));
        library.books.add(new Book("Book2", "Author2", 8));
        library.books.add(new Book("Book3", "Author3", 3));

        // Simular la entrada del usuario
        String simulatedInput = "1\n2\n1\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Llamar al método y verificar la salida
        double lateFee = library.checkoutBooks(null);
        assertEquals(0, lateFee); // No debería haber tarifas de demora simuladas en este caso
        assertEquals(2, library.books.get(0).available); // La cantidad disponible debe reducirse después del check-out
    }

    @Test
    public void testReturnBooks() {
        Library library = new Library();

        // Agregar algunos libros de muestra
        library.books.add(new Book("Book1", "Author1", 5));
        library.books.add(new Book("Book2", "Author2", 8));
        library.books.add(new Book("Book3", "Author3", 3));

        // Simular la entrada del usuario
        String simulatedInput = "Book1\n2\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Llamar al método y verificar la salida
        double lateFee = library.returnBooks(null);
        assertEquals(0, lateFee); // No debería haber tarifas de demora simuladas en este caso
        assertEquals(7, library.books.get(0).available); // La cantidad disponible debe aumentar después del retorno
    }
}
