/**
 * Tarea que simula un trabajo que tarda 1 segundo (por ejemplo, una descarga o una consulta).
 */
public class Tarea implements Runnable {
    private final int id;

    public Tarea(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        String hilo = Thread.currentThread().getName();
        System.out.println("Tarea " + id + " iniciada en el hilo: " + hilo);
        try {
            Thread.sleep(1000); // simula 1 segundo de trabajo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Tarea " + id + " finalizada en el hilo: " + hilo);
    }
}
