package Ejercicio4;

public class reserva {

    // Recurso compartido
    static class SistemaReservas {
        private int asientosDisponibles = 100;
        private final Object cerrojo = new Object();

        public boolean reservar() {
            // El hilo consulta su nombre
            String nombreHilo = Thread.currentThread().getName();

            synchronized (cerrojo) {
                if (asientosDisponibles > 0) {
                    asientosDisponibles--;
                    System.out.println(nombreHilo + " reservó con éxito. Quedan: " + asientosDisponibles);
                    return true;
                } else {
                    System.out.println(nombreHilo + " NO pudo reservar. Avión lleno.");
                    return false;
                }
            }
        }

        public int getAsientosDisponibles() {
            /*se pone tmb el synchronized para que pueda acceder de manera segura al valor antes de que otro hilo
            haga el proceso de reservar, porque puede haber 90 disponibles, pero cuando lo comprueba ya puede haber
            mucho menos debido a la velocidad que tienen los hilos
             */

            synchronized (cerrojo) {
                return asientosDisponibles;
            }
        }
    }

    // Tarea que ejecuta cada hilo
    static class AgenteVentas extends Thread {
        private SistemaReservas sistema;

        public AgenteVentas(SistemaReservas sistema, String nombre) {
            super(nombre); // Le asigna el nombre al hilo
            this.sistema = sistema;
        }

        @Override
        public void run() {
            sistema.reservar();
        }
    }

    public static void main(String[] args) {
        SistemaReservas vuelo = new SistemaReservas();
        final int TOTAL_AGENTES = 200;
        Thread[] agentes = new Thread[TOTAL_AGENTES];

        // 1. Crear y arrancar los 200 hilos
        for (int i = 0; i < TOTAL_AGENTES; i++) {
            agentes[i] = new AgenteVentas(vuelo, "Agente-" + (i + 1));
            agentes[i].start();
        }

        // 2. Esperar a que todos los hilos terminen con join()
        for (int i = 0; i < TOTAL_AGENTES; i++) {
            try {
                agentes[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 3. Comprobación final
        System.out.println("\nRESULTADO FINAL");
        System.out.println("Asientos sobrantes: " + vuelo.getAsientosDisponibles());
    }
}