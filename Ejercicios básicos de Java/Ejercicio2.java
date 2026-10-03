public class Ejercicio2 {
    public static void main(String[] args) {
        Notas notas1 = new Notas(7.9);

        System.out.println(notas1.calcularNota());
    }
}

class Notas {
    private final double nota;

    public Notas(double nota) {
        this.nota = nota;
    }

    public String calcularNota() {
        if (nota < 0 || nota > 10) {
            return "Has de introducir un número válido (0-10)";
        } else if (nota < 5) {
            return "SUSPENSO";
        } else if (nota < 7) {
            return "APROBADO";
        } else if (nota < 9) {
            return "NOTABLE";
        } else {
            return "EXCELENTE";
        }
    }
}
