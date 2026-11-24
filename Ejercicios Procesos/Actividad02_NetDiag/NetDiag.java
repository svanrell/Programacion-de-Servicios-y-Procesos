import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class NetDiag {

    private static final String AUDITOR = "TusIniciales";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce la IP o dominio a comprobar (ej. 8.8.8.8): ");
        String destino = sc.nextLine().trim();

        int numPaquetes = pedirNumPaquetes(sc);

        ProcessBuilder pb = prepararPing(destino, numPaquetes);
        ejecutar(pb);
    }

    private static int pedirNumPaquetes(Scanner sc) {
        while (true) {
            System.out.print("Introduce el número de paquetes a enviar (ej. 3): ");
            try {
                int n = Integer.parseInt(sc.nextLine().trim());
                if (n > 0) {
                    return n;
                }
                System.out.println("El número debe ser mayor que 0.");
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un número válido.");
            }
        }
    }

    private static ProcessBuilder prepararPing(String destino, int numPaquetes) {
        String os = System.getProperty("os.name").toLowerCase();
        boolean esWindows = os.contains("win");
        System.out.println("Sistema detectado: " + System.getProperty("os.name"));

        ProcessBuilder pb = new ProcessBuilder();
        List<String> comando = pb.command();

        comando.add("ping");
        comando.add(esWindows ? "-n" : "-c");
        comando.add(String.valueOf(numPaquetes));
        comando.add(destino);

        pb.directory(new File(System.getProperty("java.io.tmpdir")));
        pb.environment().put("AUDITOR_RED", AUDITOR);
        pb.inheritIO();

        return pb;
    }

    private static void ejecutar(ProcessBuilder pb) {
        System.out.print("Comando a ejecutar:");
        for (String parte : pb.command()) {
            System.out.print(" " + parte);
        }
        System.out.println();
        System.out.println("Directorio de trabajo: " + pb.directory().getAbsolutePath());

        try {
            Process proceso = pb.start();
            int codigo = proceso.waitFor();

            if (codigo == 0) {
                System.out.println("Diagnóstico correcto: hay conexión (código de salida 0).");
            } else {
                System.out.println("Fallo de conexión (código de salida " + codigo + ").");
            }
        } catch (IOException e) {
            System.out.println("Error al lanzar el proceso: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("La espera del proceso fue interrumpida.");
        }
    }
}
