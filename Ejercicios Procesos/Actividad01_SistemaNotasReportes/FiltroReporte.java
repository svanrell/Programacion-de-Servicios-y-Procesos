import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * CONSUMIDOR: lee registros NOTA:VALOR línea a línea desde la entrada estándar (System.in),
 * escribe en System.out solo los "Aprobado" y al final muestra el resumen en System.err.
 */
public class FiltroReporte {

    public static void main(String[] args) {
        int aprobados = 0;

        try (BufferedReader lector = new BufferedReader(new InputStreamReader(System.in))) {
            String linea;
            while ((linea = lector.readLine()) != null) { // null = fin de la entrada
                String[] partes = linea.trim().split(":");
                if (partes.length == 2 && partes[1].equals("Aprobado")) {
                    aprobados++;
                    System.out.println(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("[FiltroReporte] ERROR al leer la entrada: " + e.getMessage());
            System.exit(1);
        }

        System.err.println("REPORTE: Se encontraron " + aprobados + " registros Aprobado.");
    }
}
