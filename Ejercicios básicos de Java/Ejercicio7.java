public class Ejercicio7 {
    public static class Vehiculo {
        private String marca;
        private double peso;
        private double combustible;
        private double kmRecorridos;
        private final double combustibleInicial; // el combustible inicial no tiene sentido que cambie

        public Vehiculo(String marca, double peso, double combustible, double kmRecorridos) {
            this.marca = marca;
            this.peso = peso;
            this.combustible = combustible;
            this.kmRecorridos = kmRecorridos;
            this.combustibleInicial = combustible;
        }

        public Vehiculo(String marca, double peso, double combustible) {
            this(marca, peso, combustible, 0.0);
        }

        public String getMarca() {
            return marca;
        }

        public void setMarca(String marca) {
            this.marca = marca;
        }

        public double getPeso() {
            return peso;
        }

        public void setPeso(double peso) {
            this.peso = peso;
        }

        public double getCombustible() {
            return combustible;
        }

        public void setCombustible(double combustible) {
            this.combustible = combustible;
        }

        public double getKmRecorridos() {
            return kmRecorridos;
        }

        public void setKmRecorridos(double kmRecorridos) {
            this.kmRecorridos = kmRecorridos;
        }

        public double getCombustibleConsumido() {
            return Math.max(0, combustibleInicial - combustible);
        }

        public boolean puedeMoverse() {
            return combustible > 0;
        }

        public void mover() {
            if (!puedeMoverse()) {
                System.out.println(marca + " no tiene combustible para moverse.");
                return;
            }

            double gasto = this.combustible * this.peso * 0.10;
            this.combustible -= gasto;
            if (this.combustible < 0) {
                this.combustible = 0;
            }

            this.kmRecorridos += (this.peso * 0.05);

            System.out.println("[" + marca + "] se mueve -> Km: " + kmRecorridos + " km | Combustible restante: "
                    + combustible + " L (Gasto: " + gasto + " L)");
        }

        @Override
        public String toString() {
            return "Vehiculo [marca=" + marca + ", peso=" + peso + ", combustible=" + combustible
                    + ", kmRecorridos=" + kmRecorridos + "]";
        }
    }

    public static class Coche extends Vehiculo {
        private int numeroPuertas;

        public Coche(String marca, double peso, double combustible, double kmRecorridos, int numeroPuertas) {
            super(marca, peso, combustible, kmRecorridos);
            this.numeroPuertas = numeroPuertas;
        }

        public Coche(String marca, double peso, double combustible, int numeroPuertas) {
            super(marca, peso, combustible);
            this.numeroPuertas = numeroPuertas;
        }

        public int getNumeroPuertas() {
            return numeroPuertas;
        }

        public void setNumeroPuertas(int numeroPuertas) {
            this.numeroPuertas = numeroPuertas;
        }

        @Override
        public String toString() {
            return "Coche [" + super.toString() + ", numeroPuertas=" + numeroPuertas + "]";
        }
    }

    public static class Moto extends Vehiculo {
        private boolean tieneCarenado;

        public Moto(String marca, double peso, double combustible, double kmRecorridos, boolean tieneCarenado) {
            super(marca, peso, combustible, kmRecorridos);
            this.tieneCarenado = tieneCarenado;
        }

        public Moto(String marca, double peso, double combustible, boolean tieneCarenado) {
            super(marca, peso, combustible);
            this.tieneCarenado = tieneCarenado;
        }

        public boolean isTieneCarenado() {
            return tieneCarenado;
        }

        public boolean getTieneCarenado() {
            return tieneCarenado;
        }

        public void setTieneCarenado(boolean tieneCarenado) {
            this.tieneCarenado = tieneCarenado;
        }

        @Override
        public String toString() {
            return "Moto [" + super.toString() + ", tieneCarenado=" + (tieneCarenado ? "Sí" : "No") + "]";
        }
    }

    public static class Carrera {
        private final Vehiculo vehiculo1;
        private final Vehiculo vehiculo2;
        private final double kilometrajeMeta;

        public Carrera(Vehiculo vehiculo1, Vehiculo vehiculo2, double kilometrajeMeta) {
            this.vehiculo1 = vehiculo1;
            this.vehiculo2 = vehiculo2;
            this.kilometrajeMeta = kilometrajeMeta;
        }

        public void iniciarCarrera() {
            System.out.println("INICIO DE LA CARRERA");
            System.out.println(" Meta fijada: " + kilometrajeMeta + " km");
            System.out.println(" Competidor 1: " + vehiculo1.getMarca() + " (Peso: " + vehiculo1.getPeso()
                    + ", Combustible: " + vehiculo1.getCombustible() + " L)");
            System.out.println(" Competidor 2: " + vehiculo2.getMarca() + " (Peso: " + vehiculo2.getPeso()
                    + ", Combustible: " + vehiculo2.getCombustible() + " L)");

            int turno = 1;

            while (vehiculo1.getKmRecorridos() < kilometrajeMeta && vehiculo2.getKmRecorridos() < kilometrajeMeta
                    && (vehiculo1.puedeMoverse() || vehiculo2.puedeMoverse())) {

                System.out.println("\nTurno " + turno);

                if (vehiculo1.puedeMoverse() && vehiculo1.getKmRecorridos() < kilometrajeMeta) {
                    vehiculo1.mover();
                } else if (!vehiculo1.puedeMoverse()) {
                    System.out.println(vehiculo1.getMarca() + " no puede avanzar (sin combustible).");
                }

                if (vehiculo2.puedeMoverse() && vehiculo2.getKmRecorridos() < kilometrajeMeta) {
                    vehiculo2.mover();
                } else if (!vehiculo2.puedeMoverse()) {
                    System.out.println(vehiculo2.getMarca() + " no puede avanzar (sin combustible).");
                }

                System.out.print(">> Posición actual: ");
                if (vehiculo1.getKmRecorridos() > vehiculo2.getKmRecorridos()) {
                    System.out.println("Va ganando " + vehiculo1.getMarca() + " (" + vehiculo1.getKmRecorridos()
                            + " km vs " + vehiculo2.getKmRecorridos() + " km)");
                } else if (vehiculo2.getKmRecorridos() > vehiculo1.getKmRecorridos()) {
                    System.out.println("Va ganando " + vehiculo2.getMarca() + " (" + vehiculo2.getKmRecorridos()
                            + " km vs " + vehiculo1.getKmRecorridos() + " km)");
                } else {
                    System.out.println("Empate provisional (ambos a " + vehiculo1.getKmRecorridos() + " km)");
                }

                System.out.println(">> Combustible utilizado: " + vehiculo1.getMarca() + " = "
                        + vehiculo1.getCombustibleConsumido() + " L | "
                        + vehiculo2.getMarca() + " = " + vehiculo2.getCombustibleConsumido() + " L");

                turno++;
            }
            System.out.println("RESULTADO FINAL");

            boolean v1Gano = vehiculo1.getKmRecorridos() >= kilometrajeMeta;
            boolean v2Gano = vehiculo2.getKmRecorridos() >= kilometrajeMeta;

            if (v1Gano && v2Gano) {
                if (vehiculo1.getKmRecorridos() > vehiculo2.getKmRecorridos()) {
                    System.out.println("El ganador es " + vehiculo1.getMarca() + "!");
                } else if (vehiculo2.getKmRecorridos() > vehiculo1.getKmRecorridos()) {
                    System.out.println("El ganador es " + vehiculo2.getMarca() + "!");
                } else {
                    System.out.println("Ha ocurrido un empate en la meta!");
                }
            } else if (v1Gano) {
                System.out.println("¡El ganador es " + vehiculo1.getMarca() + " al cruzar la meta!");
            } else if (v2Gano) {
                System.out.println("¡El ganador es " + vehiculo2.getMarca() + " al cruzar la meta!");
            } else {
                System.out.println("Ambos vehículos se han quedado sin combustible antes de alcanzar la meta.");
                if (vehiculo1.getKmRecorridos() > vehiculo2.getKmRecorridos()) {
                    System.out.println("Llegó más lejos " + vehiculo1.getMarca() + " ("
                            + vehiculo1.getKmRecorridos() + " km).");
                } else if (vehiculo2.getKmRecorridos() > vehiculo1.getKmRecorridos()) {
                    System.out.println("Llegó más lejos " + vehiculo2.getMarca() + " ("
                            + vehiculo2.getKmRecorridos() + " km).");
                } else {
                    System.out.println("Ambos llegaron exactamente a la misma distancia.");
                }
            }

            System.out.println("Combustible total consumido por " + vehiculo1.getMarca() + ": "
                    + vehiculo1.getCombustibleConsumido() + " L");
            System.out.println("Combustible total consumido por " + vehiculo2.getMarca() + ": "
                    + vehiculo2.getCombustibleConsumido() + " L");
        }
    }

    public static void main(String[] args) {
        System.out.println("COMPROBACIÓN INDIVIDUAL DEL MÉTODO MOVER");

        Coche coche = new Coche("Toyota Corolla", 1.5, 50.0, 5);
        Moto moto = new Moto("Yamaha MT-07", 0.8, 20.0, true);

        System.out.println("Estado inicial:");
        System.out.println(" - " + coche);
        System.out.println(" - " + moto);

        System.out.println("\nLlamando al método mover() en el coche:");
        coche.mover();

        System.out.println("\nLlamando al método mover() en la moto:");
        moto.mover();

        System.out.println("\nEstado tras moverse una vez:");
        System.out.println(" - " + coche);
        System.out.println(" - " + moto);

        System.out.println("\nSIMULACIÓN DE CARRERA");
        Coche cocheCarrera = new Coche("Seat León", 1.4, 60.0, 5);
        Moto motoCarrera = new Moto("Honda CBR", 0.9, 30.0, true);

        Carrera carrera = new Carrera(cocheCarrera, motoCarrera, 0.5);
        carrera.iniciarCarrera();
    }
}
