public class EjecucionSecuencial {
    public static void main(String[] args) {
        long inicio = System.currentTimeMillis();

        for (int i = 1; i <= 5; i++) {
            new Tarea(i).run();
        }

        long fin = System.currentTimeMillis();
        System.out.println("Tiempo total (secuencial): " + (fin - inicio) + " ms");
    }
}
