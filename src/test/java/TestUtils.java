import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestUtils {

    public static String[] captureSystemOut(Runnable code) {
        // Guardar la salida estándar original
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            // Ejecutar el código
            code.run();
            System.out.flush();
        } finally {
            // Restaurar la salida estándar original
            System.setOut(originalOut);
        }

        // Dividir la salida capturada por líneas y devolver un array de líneas
        return outputStream.toString().split(System.lineSeparator());
    }
}
