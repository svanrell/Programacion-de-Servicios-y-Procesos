public class Ejercicio3 {
    public static void main(String[] args) {
        Tablas tabla1 = new Tablas(9);

        tabla1.calcularTablaMultiplicar();
        System.out.println("\nSuma de los números pares del 1 al 20: " + tabla1.calcularSumaNumeros());
    }
}

class Tablas {
    private final int numero;

    public Tablas(int numero) {
        this.numero = numero;
    }

    public void calcularTablaMultiplicar() {
        System.out.println("Tabla del " + this.numero + ":");
        for (int i = 1; i <= 10; i++) {
            System.out.println(this.numero + " x " + i + " = " + (this.numero * i));
        }
    }

    public int calcularSumaNumeros() {
        int i = 1;
        int totalNumeros = 0;
        while (i <= 20) {
            if (i % 2 == 0) {
                totalNumeros += i;
            }
            i++;
        }
        return totalNumeros;
    }
}
