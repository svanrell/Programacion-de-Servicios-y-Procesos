public class EjecucionConcurrente {
    public static void main(String[] args) throws InterruptedException {
        long inicio = System.currentTimeMillis();

        Thread[] hilos = new Thread[5];
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new Tarea(i + 1));
            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            hilo.join();
        }

        long fin = System.currentTimeMillis();
        System.out.println("Tiempo total (concurrente): " + (fin - inicio) + " ms");
    }
}
