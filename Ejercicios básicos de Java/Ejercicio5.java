public class Ejercicio5 {

    public static void main(String[] args) {
        Personaje heroe = new Personaje("Héroe Arturo", 5, 100.0);
        System.out.println("Estado inicial del héroe:");
        System.out.println(heroe);

        System.out.println("\n--- Simulando varios ataques al héroe ---");
        System.out.println("\n[Ataque 1]");
        heroe.recibirDanio(30.0);

        System.out.println("\n[Ataque 2]");
        heroe.recibirDanio(45.5);

        System.out.println("\n[Ataque 3]");
        heroe.recibirDanio(35.0); // La vida llega a <= 0 y avisa que ha sido derrotado

        System.out.println("\n[Ataque 4 (intento tras ser derrotado)]");
        heroe.recibirDanio(10.0);

        System.out.println("\nEstado final del héroe:");
        System.out.println(heroe);

        // SIMULACIÓN:
        System.out.println("  SIMULACIÓN: COMBATE ENTRE DOS RIVALES");

        Personaje personaje1 = new Personaje("Sergi", 1, 100.0, 25.0);
        Personaje personaje2 = new Personaje("Luis", 1, 100.0, 20.0);

        jugarPartida(personaje1, personaje2);
    }

    public static void jugarPartida(Personaje personaje1, Personaje personaje2) {
        if (personaje1 == null || personaje2 == null) {
            System.out.println("Uno o más personajes son null");
            return;
        }

        System.out.println("Luchadores listos:");
        System.out.println(" - " + personaje1);
        System.out.println(" - " + personaje2);
        System.out.println("\n¡Comienza el combate!");

        int ronda = 1;
        while (personaje1.getPuntosVida() > 0 && personaje2.getPuntosVida() > 0) {
            System.out.println("\n--- Ronda " + ronda + " ---");

            // Personaje 1 ataca a Personaje 2
            System.out.println(personaje1.getNombre() + " ataca a " + personaje2.getNombre() + ":");
            personaje2.recibirDanio(personaje1.getDanio());

            // Si el personaje 2 sigue con vida, contraataca
            if (personaje2.getPuntosVida() > 0) {
                System.out.println(personaje2.getNombre() + " contraataca a " + personaje1.getNombre() + ":");
                personaje1.recibirDanio(personaje2.getDanio());
            }

            ronda++;
        }

        System.out.println("\nRESULTADO FINAL");
        if (personaje1.getPuntosVida() > 0) {
            System.out.println("¡El vencedor del combate es " + personaje1.getNombre() + "!");
        } else if (personaje2.getPuntosVida() > 0) {
            System.out.println("¡El vencedor del combate es " + personaje2.getNombre() + "!");
        } else {
            System.out.println("¡El combate ha terminado en empate!");
        }
    }
}

class Personaje {
    private String nombre;
    private int nivel;
    private double puntosVida;
    private double danio;

    public Personaje(String nombre, int nivel, double puntosVida) {
        this(nombre, nivel, puntosVida, 10.0);
    }

    public Personaje(String nombre, int nivel, double puntosVida, double danio) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.puntosVida = puntosVida;
        this.danio = danio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public double getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(double puntosVida) {
        this.puntosVida = puntosVida;
    }

    public double getDanio() {
        return danio;
    }

    public void setDanio(double danio) {
        this.danio = danio;
    }

    public void recibirDanio(double cantidad) {
        if (this.puntosVida <= 0) {
            System.out.println(this.nombre + " ya ha sido derrotado previamente.");
            return;
        }

        this.puntosVida -= cantidad;

        if (this.puntosVida <= 0) {
            this.puntosVida = 0;
            System.out.println(this.nombre + " ha recibido " + cantidad + " de daño. Vida restante: " + this.puntosVida);
            System.out.println("¡ALERTA: " + this.nombre + " ha sido derrotado! (vida <= 0)");
        } else {
            System.out.println(this.nombre + " ha recibido " + cantidad + " de daño. Vida restante: " + this.puntosVida);
        }
    }

    public void subirNivelRandom() {
        this.nivel += (int) (Math.random() * 10) + 1;
    }

    public void hacerDanioRandom() {
        this.danio += (int) (Math.random() * 10) + 1;
    }

    @Override
    public String toString() {
        return "Personaje [Nombre: " + this.nombre + ", Nivel: " + this.nivel + ", Puntos de Vida: " + this.puntosVida + ", Daño: " + this.danio + "]";
    }
}