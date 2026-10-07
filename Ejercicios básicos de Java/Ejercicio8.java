import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio8 {

    public static class Persona {
        private String nombre;
        private double dinero;
        private boolean tieneSuscripcion = false;
        private int diasActivosSuscripcion = 0;
        private double gastoAcumulado = 0.0;

        public Persona(String nombre, double dinero, boolean tieneSuscripcion) {
            this.nombre = nombre;
            this.dinero = dinero;
            this.tieneSuscripcion = tieneSuscripcion;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public double getDinero() {
            return dinero;
        }

        public void setDinero(double dinero) {
            this.dinero = dinero;
        }

        public boolean tieneSuscripcion() {
            return tieneSuscripcion;
        }

        public void setTieneSuscripcion(boolean tieneSuscripcion) {
            this.tieneSuscripcion = tieneSuscripcion;
        }

        public int getDiasActivosSuscripcion() {
            return diasActivosSuscripcion;
        }

        public void setDiasActivosSuscripcion(int diasActivosSuscripcion) {
            this.diasActivosSuscripcion = diasActivosSuscripcion;
        }

        public double getGastoAcumulado() {
            return gastoAcumulado;
        }

        public void setGastoAcumulado(double gastoAcumulado) {
            this.gastoAcumulado = gastoAcumulado;
        }
    }

    public static abstract class Contenido {
        private String titulo;
        private int duracionMinutos;
        private String genero;
        private LocalDate fechaInicio;
        private LocalDate fechaFinal;

        public Contenido(String titulo, int duracionMinutos, String genero, LocalDate fechaInicio, LocalDate fechaFinal) {
            this.titulo = titulo;
            this.duracionMinutos = duracionMinutos;
            this.genero = genero;
            this.fechaInicio = fechaInicio;
            this.fechaFinal = fechaFinal;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public int getDuracionMinutos() {
            return duracionMinutos;
        }

        public void setDuracionMinutos(int duracionMinutos) {
            this.duracionMinutos = duracionMinutos;
        }

        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }

        public LocalDate getFechaInicio() {
            return fechaInicio;
        }

        public void setFechaInicio(LocalDate fechaInicio) {
            this.fechaInicio = fechaInicio;
        }

        public LocalDate getFechaFinal() {
            return fechaFinal;
        }

        public void setFechaFinal(LocalDate fechaFinal) {
            this.fechaFinal = fechaFinal;
        }

        public long getDiasActivosSuscripcion() {
            if (fechaInicio != null && fechaFinal != null) {
                return ChronoUnit.DAYS.between(fechaInicio, fechaFinal);
            }
            return 0;
        }

        public String reproducir() {
            return "Se ha iniciado la reproduccion del titulo: " + this.titulo + " con una duracion de: " + this.duracionMinutos + " m";
        }
    }

    public static class Pelicula extends Contenido {
        private String director;
        public static final double CUOTA_MENSUAL_BASE = 20;
        private boolean tieneSuscripcion = false;

        public Pelicula(String titulo, int duracionMinutos, String genero, String director, boolean tieneSuscripcion, LocalDate fechaInicio, LocalDate fechaFinal) {
            super(titulo, duracionMinutos, genero, fechaInicio, fechaFinal);
            this.director = director;
            this.tieneSuscripcion = tieneSuscripcion;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public double comprarSuscripcion(double dinero) {
            if (dinero >= CUOTA_MENSUAL_BASE) {
                dinero -= CUOTA_MENSUAL_BASE;
                tieneSuscripcion = true;
                setFechaInicio(LocalDate.now());
                setFechaFinal(getFechaInicio().plusMonths(1));
            } else {
                tieneSuscripcion = false;
            }
            return dinero;
        }

        public boolean tieneSuscripcion() {
            return getFechaFinal() != null && getFechaFinal().isAfter(LocalDate.now());
        }

        public void setTieneSuscripcion(boolean tieneSuscripcion) {
            this.tieneSuscripcion = tieneSuscripcion;
        }

        public double getCuotaMensualBase() {
            return CUOTA_MENSUAL_BASE;
        }

        @Override
        public String reproducir() {
            return super.reproducir() + " dirigida por " + this.director;
        }
    }

    public static class Serie extends Contenido {
        private int numeroTemporadas;
        private int capitulosTotales;
        public static final double CUOTA_MENSUAL_BASE = 35;
        private boolean tieneSuscripcion = false;

        public Serie(String titulo, int duracionMinutos, String genero, int numeroTemporadas, int capitulosTotales, boolean tieneSuscripcion, LocalDate fechaInicio, LocalDate fechaFinal) {
            super(titulo, duracionMinutos, genero, fechaInicio, fechaFinal);
            this.numeroTemporadas = numeroTemporadas;
            this.capitulosTotales = capitulosTotales;
            this.tieneSuscripcion = tieneSuscripcion;
        }

        public int getNumeroTemporadas() {
            return numeroTemporadas;
        }

        public void setNumeroTemporadas(int numeroTemporadas) {
            this.numeroTemporadas = numeroTemporadas;
        }

        public int getCapitulosTotales() {
            return capitulosTotales;
        }

        public void setCapitulosTotales(int capitulosTotales) {
            this.capitulosTotales = capitulosTotales;
        }

        public double comprarSuscripcion(double dinero) {
            if (dinero >= CUOTA_MENSUAL_BASE) {
                dinero -= CUOTA_MENSUAL_BASE;
                tieneSuscripcion = true;
                setFechaInicio(LocalDate.now());
                setFechaFinal(getFechaInicio().plusMonths(1));
            } else {
                tieneSuscripcion = false;
            }
            return dinero;
        }

        public boolean tieneSuscripcion() {
            return getFechaFinal() != null && getFechaFinal().isAfter(LocalDate.now());
        }

        public void setTieneSuscripcion(boolean tieneSuscripcion) {
            this.tieneSuscripcion = tieneSuscripcion;
        }

        public double getCuotaMensualBase() {
            return CUOTA_MENSUAL_BASE;
        }

        @Override
        public String reproducir() {
            return super.reproducir() + " tiene " + getNumeroTemporadas() + " temporadas con " + getCapitulosTotales() + " capitulos";
        }
    }

    public static void main(String[] args) {
        Persona usuario = new Persona("Carlos", 100.0, false);

        Pelicula pelicula = new Pelicula("Interstellar", 169, "Ciencia Ficcion", "Christopher Nolan", false, null, null);
        Serie serie = new Serie("Stranger Things", 50, "Ciencia Ficcion", 4, 34, false, null, null);

        double dineroInicial = usuario.getDinero();
        usuario.setDinero(pelicula.comprarSuscripcion(usuario.getDinero()));
        usuario.setDinero(serie.comprarSuscripcion(usuario.getDinero()));

        usuario.setGastoAcumulado(dineroInicial - usuario.getDinero());
        usuario.setDiasActivosSuscripcion((int) pelicula.getDiasActivosSuscripcion());
        usuario.setTieneSuscripcion(pelicula.tieneSuscripcion() || serie.tieneSuscripcion());

        System.out.println("Usuario: " + usuario.getNombre());
        System.out.println("Cuota base pelicula: " + Pelicula.CUOTA_MENSUAL_BASE);
        System.out.println("Cuota base serie: " + Serie.CUOTA_MENSUAL_BASE);
        System.out.println("Dias activos de suscripcion: " + usuario.getDiasActivosSuscripcion());
        System.out.println("Gasto acumulado: " + usuario.getGastoAcumulado());
        System.out.println("Dinero restante: " + usuario.getDinero());
        System.out.println("Pelicula suscripcion activa: " + pelicula.tieneSuscripcion() + " (desde " + pelicula.getFechaInicio() + " hasta " + pelicula.getFechaFinal() + ")");
        System.out.println("Serie suscripcion activa: " + serie.tieneSuscripcion() + " (desde " + serie.getFechaInicio() + " hasta " + serie.getFechaFinal() + ")");

        List<Contenido> catalogo = new ArrayList<>();
        catalogo.add(pelicula);
        catalogo.add(serie);

        for (Contenido contenido : catalogo) {
            System.out.println(contenido.reproducir());

            if (contenido.getDuracionMinutos() > 120) {
                System.out.println("Recomendacion: La duracion supera los 120 minutos, se sugiere una pausa.");
            } else {
                System.out.println("Recomendacion: Duracion adecuada para ver de una vez.");
            }
        }
    }
}
