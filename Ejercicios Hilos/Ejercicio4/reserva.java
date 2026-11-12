package Ejercicio4;

public class reserva {

    static class SistemaReservas {
        private int asientosDisponibles = 100;
        private final Object cerrojo = new Object();

        public boolean reservar() {
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
            synchronized (cerrojo) {
                return asientosDisponibles;
            }
        }
    }

    static class AgenteVentas extends Thread {
        private SistemaReservas sistema;

        public AgenteVentas(SistemaReservas sistema, String nombre) {
            super(nombre);
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

        for (int i = 0; i < TOTAL_AGENTES; i++) {
            agentes[i] = new AgenteVentas(vuelo, "Agente-" + (i + 1));
            agentes[i].start();
        }

        for (int i = 0; i < TOTAL_AGENTES; i++) {
            try {
                agentes[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nRESULTADO FINAL");
        System.out.println("Asientos sobrantes: " + vuelo.getAsientosDisponibles());
    }
}
