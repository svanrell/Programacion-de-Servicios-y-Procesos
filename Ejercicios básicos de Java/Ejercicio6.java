public class Ejercicio6 {
    public static class Empleado {
        private String nombre;
        private double salarioBase;

        public Empleado(String nombre, double salarioBase) {
            this.nombre = nombre;
            this.salarioBase = salarioBase;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public double calcularSalario() {
            return salarioBase;
        }

        public void setSalarioBase(double salarioBase) {
            this.salarioBase = salarioBase;
        }
    }

    public static class Vendedor extends Empleado {
        private double comision;

        public Vendedor(String nombre, double salarioBase, double comision) {
            super(nombre, salarioBase);
            this.comision = comision;
        }

        @Override
        public double calcularSalario() {
            return super.calcularSalario() + comision;
        }
    }

    public static void main(String[] args) {
            Empleado empleado = new Empleado("Ana", 2000);
            Vendedor vendedor = new Vendedor("Luis", 1500, 300);

            System.out.println(empleado.getNombre() + ": $" + empleado.calcularSalario());
            System.out.println(vendedor.getNombre() + ": $" + vendedor.calcularSalario());
    }

}

