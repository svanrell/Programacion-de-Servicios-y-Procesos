import java.util.Random;

public class GeneradorNotas {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("[GeneradorNotas] ERROR: uso -> java GeneradorNotas <numeroDeNotas>");
            System.exit(1);
        }

        int total;
        try {
            total = Integer.parseInt(args[0]);
            if (total < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.err.println("[GeneradorNotas] ERROR: '" + args[0] + "' no es un número válido.");
            System.exit(1);
            return;
        }

        System.err.println("[GeneradorNotas] Inicio: se van a generar " + total + " registros.");

        Random random = new Random();
        for (int i = 0; i < total; i++) {
            int nota = random.nextInt(0, 11);
            String valor = (nota >= 5) ? "Aprobado" : "Suspendido";
            System.out.println(nota + ":" + valor);
        }

        System.err.println("[GeneradorNotas] Fin: se han generado " + total + " registros.");
    }
}
