import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FiltroReporte {

    public static void main(String[] args) {
        int aprobados = 0;

        try (BufferedReader lector = new BufferedReader(new InputStreamReader(System.in))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
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
