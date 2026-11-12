/**
 * Ejecuta 5 tareas una detrás de otra en el hilo main.
 * Tiempo esperado: unos 5000 ms.
 */
public class EjecucionSecuencial {
    public static void main(String[] args) {
        long inicio = System.currentTimeMillis();

        for (int i = 1; i <= 5; i++) {
            new Tarea(i).run(); // llamada normal a run(): NO crea un hilo nuevo
        }

        long fin = System.currentTimeMillis();
        System.out.println("Tiempo total (secuencial): " + (fin - inicio) + " ms");
    }
}
