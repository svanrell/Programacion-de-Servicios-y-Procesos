import java.util.Random;

public class DescargaArchivo implements Runnable {

    private static final long DURACION_DESCARGA_MS = 3000;
    private static final Random random = new Random();

    private final int numSolicitud;
    private final ServicioDescargas servicio;

    public DescargaArchivo(int numSolicitud, ServicioDescargas servicio) {
        this.numSolicitud = numSolicitud;
        this.servicio = servicio;
    }

    @Override
    public void run() {
        String archivo = "archivo_" + numSolicitud + ".zip";
        String hilo = Thread.currentThread().getName();
        try {
            servicio.getSemaforo().acquire();
            try {
                int tokens = random.nextInt(1, 6);
                if (!servicio.consumirTokens(tokens)) {
                    System.out.println("Solicitud #" + numSolicitud + " rechazada: necesita " + tokens
                            + " tokens y solo quedan " + servicio.getTokensDisponibles() + ".");
                    return;
                }

                System.out.println("Descargando " + archivo + " en " + hilo
                        + " (consume " + tokens + " tokens, quedan " + servicio.getTokensDisponibles() + ")");

                Thread.sleep(DURACION_DESCARGA_MS);

                int total = servicio.registrarDescarga();
                System.out.println("Descarga #" + numSolicitud + " completada. (total descargas: " + total + ")");
            } finally {
                servicio.getSemaforo().release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Descarga #" + numSolicitud + " interrumpida.");
        }
    }
}
