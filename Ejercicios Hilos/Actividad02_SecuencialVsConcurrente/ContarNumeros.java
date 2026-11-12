/**
 * Pregunta 4: cuenta (suma) los números del 1 al 1000 de forma secuencial
 * y de forma concurrente con varios hilos, y compara los tiempos.
 */
public class ContarNumeros {
    private static final int LIMITE = 1000;
    private static final int NUM_HILOS = 4;

    public static void main(String[] args) throws InterruptedException {
        // --- Versión secuencial ---
        long inicioSec = System.nanoTime();
        long sumaSec = 0;
        for (int i = 1; i <= LIMITE; i++) {
            sumaSec += i;
        }
        long tiempoSec = System.nanoTime() - inicioSec;

        // --- Versión concurrente ---
        long inicioConc = System.nanoTime();
        long[] parciales = new long[NUM_HILOS]; // cada hilo escribe solo en su posición: no hay condición de carrera
        Thread[] hilos = new Thread[NUM_HILOS];
        int tramo = LIMITE / NUM_HILOS;

        for (int h = 0; h < NUM_HILOS; h++) {
            final int indice = h;
            final int desde = h * tramo + 1;
            final int hasta = (h == NUM_HILOS - 1) ? LIMITE : (h + 1) * tramo;
            hilos[h] = new Thread(() -> {
                long suma = 0;
                for (int i = desde; i <= hasta; i++) {
                    suma += i;
                }
                parciales[indice] = suma;
            });
            hilos[h].start();
        }
        for (Thread hilo : hilos) {
            hilo.join();
        }
        long sumaConc = 0;
        for (long p : parciales) {
            sumaConc += p;
        }
        long tiempoConc = System.nanoTime() - inicioConc;

        // --- Resultados ---
        System.out.println("Secuencial  -> suma = " + sumaSec + " | tiempo = " + tiempoSec / 1000 + " µs");
        System.out.println("Concurrente -> suma = " + sumaConc + " | tiempo = " + tiempoConc / 1000 + " µs (" + NUM_HILOS + " hilos)");

        if (tiempoConc > tiempoSec) {
            System.out.println("La versión concurrente ha sido " + (tiempoConc / Math.max(tiempoSec, 1)) + " veces MÁS LENTA.");
        } else {
            System.out.println("La versión concurrente ha sido más rápida.");
        }
    }
}
