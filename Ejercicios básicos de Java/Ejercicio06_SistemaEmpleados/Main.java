public class Main {
    public static void main(String[] args) {
        Empleado empleado = new Empleado("Ana", 1500.0);
        Vendedor vendedor = new Vendedor("Luis", 1300.0, 450.0);

        System.out.println("Salario de " + empleado.getNombre() + " (Empleado): " + empleado.calcularSalario() + " €");
        System.out.println("Salario de " + vendedor.getNombre() + " (Vendedor): " + vendedor.calcularSalario() + " €");
    }
}
