import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Servicio de descargas.
 * - Recibe una solicitud cada 5 segundos.
 * - Solo atiende 2 descargas a la vez (Semaphore con 2 permisos).
 * - Empieza con 50 tokens; cada descarga consume entre 1 y 5.
 * - Se detiene tras 20 descargas o cuando se acaban los tokens.
 */
public class ServicioDescargas {

    public static final int TOKENS_INICIALES = 50;
    public static final int MAX_DESCARGAS = 20;
    public static final int MAX_SIMULTANEAS = 2;
    public static final long INTERVALO_SOLICITUDES_MS = 5000;

    // Recursos compartidos entre los hilos
    private int tokensDisponibles = TOKENS_INICIALES;
    private int descargasRealizadas = 0;
    private volatile boolean activo = true;

    // Semáforo: como máximo 2 hilos descargando a la vez
    private final Semaphore semaforo = new Semaphore(MAX_SIMULTANEAS);

    public Semaphore getSemaforo() {
        return semaforo;
    }

    /**
     * Intenta reservar tokens para una descarga.
     * synchronized: solo un hilo a la vez puede leer y modificar los tokens,
     * así evitamos condiciones de carrera.
     *
     * @return true si había tokens suficientes y se han descontado.
     */
    public synchronized boolean consumirTokens(int cantidad) {
        if (!activo || tokensDisponibles < cantidad) {
            activo = false; // sin tokens suficientes: el servicio deja de aceptar solicitudes
            return false;
        }
        tokensDisponibles -= cantidad;
        return true;
    }

    /** Suma una descarga al total del servidor (sección crítica). */
    public synchronized int registrarDescarga() {
        descargasRealizadas++;
        return descargasRealizadas;
    }

    public synchronized int getTokensDisponibles() {
        return tokensDisponibles;
    }

    public synchronized int getDescargasRealizadas() {
        return descargasRealizadas;
    }

    public boolean isActivo() {
        return activo;
    }

    /** Bucle principal: escucha (simula) nuevas peticiones mientras quede servicio. */
    public void iniciar() throws InterruptedException {
        System.out.println("Servicio de descargas iniciado... (tokens: " + TOKENS_INICIALES + ")");
        List<Thread> hilos = new ArrayList<>();
        int numSolicitud = 0;

        while (activo && numSolicitud < MAX_DESCARGAS && getTokensDisponibles() > 0) {
            numSolicitud++;
            System.out.println("\nNueva solicitud de descarga #" + numSolicitud);

            // Cada solicitud la atiende un hilo nuevo; el servicio sigue escuchando
            Thread hilo = new Thread(new DescargaArchivo(numSolicitud, this));
            hilos.add(hilo);
            hilo.start();

            Thread.sleep(INTERVALO_SOLICITUDES_MS); // llega la siguiente solicitud en 5 s
        }

        // Esperamos a que terminen las descargas que estén en curso
        for (Thread hilo : hilos) {
            hilo.join();
        }

        System.out.println("\n----------------------------------------");
        if (getDescargasRealizadas() >= MAX_DESCARGAS) {
            System.out.println("Motivo: se ha alcanzado el máximo de " + MAX_DESCARGAS + " descargas.");
        } else {
            System.out.println("Motivo: no quedan tokens suficientes.");
        }
        System.out.println("Descargas realizadas: " + getDescargasRealizadas());
        System.out.println("Tokens restantes: " + getTokensDisponibles());
        System.out.println("Servicio detenido");
    }

    public static void main(String[] args) throws InterruptedException {
        new ServicioDescargas().iniciar();
    }
}
