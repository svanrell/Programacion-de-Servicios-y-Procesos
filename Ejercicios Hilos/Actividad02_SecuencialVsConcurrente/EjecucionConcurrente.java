/**
 * Ejecuta 5 tareas a la vez, cada una en su propio hilo.
 * Tiempo esperado: unos 1000 ms.
 */
public class EjecucionConcurrente {
    public static void main(String[] args) throws InterruptedException {
        long inicio = System.currentTimeMillis();

        Thread[] hilos = new Thread[5];
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new Tarea(i + 1));
            hilos[i].start(); // start() crea un hilo nuevo que ejecuta run()
        }

        // main espera a que terminen todos los hilos antes de medir el tiempo
        for (Thread hilo : hilos) {
            hilo.join();
        }

        long fin = System.currentTimeMillis();
        System.out.println("Tiempo total (concurrente): " + (fin - inicio) + " ms");
    }
}
