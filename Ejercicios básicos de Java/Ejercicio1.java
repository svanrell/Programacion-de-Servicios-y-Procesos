public class Ejercicio1 {

    public static void main(String[] args) {
        double precioBase = 25.50;
        int cantidadStock = 100;

        Calculadora calculadora = new Calculadora(cantidadStock, precioBase);

        double totalSinDescuento = calculadora.calcularTotalSinDescuento();
        double descuentoAplicado = calculadora.calcularDescuento();
        double totalFinal = calculadora.calcularTotalFinal();

        System.out.println("Precio base del producto: " + precioBase + " €");
        System.out.println("Cantidad en stock: " + cantidadStock);
        System.out.println("Tasa de descuento: " + (Calculadora.FINAL_DESCUENTO * 100) + "%");
        System.out.println("Total sin descuento: " + totalSinDescuento + " €");
        System.out.println("Descuento aplicado: " + descuentoAplicado + " €");
        System.out.println("Valor total final del inventario: " + totalFinal + " €");
    }
}

class Calculadora {
    private int cantidadStock;
    private double precioProducto;

    // El final no se puede modificar ni en el constructor ni en métodos de la clase, por tanto, se hace public para que se pueda acceder por fuera de la clase directamente y se hace estática para que se instance una vez
    public static final double FINAL_DESCUENTO = 0.10;
    public static final double IVA = 0.21;

    public Calculadora(int cantidadStock, double precioProducto) {
        this.cantidadStock = cantidadStock;
        this.precioProducto = precioProducto;
    }

    public double calcularTotalSinDescuento() {
        return this.cantidadStock * this.precioProducto;
    }

    public double calcularDescuento() {
        return calcularTotalSinDescuento() * FINAL_DESCUENTO;
    }

    public double calcularTotalFinal() {
        return calcularTotalSinDescuento() - calcularDescuento();
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public double getFinalDescuento() {
        return FINAL_DESCUENTO;
    }

    public double getIva() {
        return IVA;
    }
}
