class Coche {
    private String marca;
    private String modelo;
    private int anio;


    public Coche(String marca, String modelo, int anio) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "Coche {" + "marca=" + marca + ", modelo=" + modelo + ", anio=" + anio + "}";
    }
}


public class Ejercicio4 {
    public static void main(String[] args) {
        Coche coche1 = new Coche("Seat", "Exeo", 2015);
        System.out.println(coche1);
    }
}