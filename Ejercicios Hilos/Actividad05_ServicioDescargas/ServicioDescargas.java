import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ServicioDescargas {

    public static final int TOKENS_INICIALES = 50;
    public static final int MAX_DESCARGAS = 20;
    public static final int MAX_SIMULTANEAS = 2;
    public static final long INTERVALO_SOLICITUDES_MS = 5000;

    private int tokensDisponibles = TOKENS_INICIALES;
    private int descargasRealizadas = 0;
    private volatile boolean activo = true;

    private final Semaphore semaforo = new Semaphore(MAX_SIMULTANEAS);

    public Semaphore getSemaforo() {
        return semaforo;
    }

    public synchronized boolean consumirTokens(int cantidad) {
        if (!activo || tokensDisponibles < cantidad) {
            activo = false;
            return false;
        }
        tokensDisponibles -= cantidad;
        return true;
    }

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

    public void iniciar() throws InterruptedException {
        System.out.println("Servicio de descargas iniciado... (tokens: " + TOKENS_INICIALES + ")");
        List<Thread> hilos = new ArrayList<>();
        int numSolicitud = 0;

        while (activo && numSolicitud < MAX_DESCARGAS && getTokensDisponibles() > 0) {
            numSolicitud++;
            System.out.println("\nNueva solicitud de descarga #" + numSolicitud);

            Thread hilo = new Thread(new DescargaArchivo(numSolicitud, this));
            hilos.add(hilo);
            hilo.start();

            Thread.sleep(INTERVALO_SOLICITUDES_MS);
        }

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
