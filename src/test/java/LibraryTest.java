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
    public void testAddBook() {
        Library library = new Library();

        // Agregar un libro de muestra
        Book book = new Book("SampleBook", "SampleAuthor", 3);
        library.books.add(book);

        // Verificar que el libro se ha agregado correctamente
        assertEquals(1, library.books.size());
        assertSame(book, library.books.get(0));
    }

    @Test
    public void testDisplayCatalog() {
        Library library = new Library();

        // Agregar algunos libros de muestra
        library.books.add(new Book("Book1", "Author1", 5));
        library.books.add(new Book("Book2", "Author2", 8));

        // Capturar la salida estándar durante la ejecución de displayCatalog
        String[] capturedOutput = TestUtils.captureSystemOut(() -> library.displayCatalog());

        // Verificar que la salida contiene información sobre los libros
        assertTrue(capturedOutput[0].contains("Book1 by Author1 - Available: 5"));
        assertTrue(capturedOutput[0].contains("Book2 by Author2 - Available: 8"));
    }

    // Agrega más pruebas según sea necesario
}
