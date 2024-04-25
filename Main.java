import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al Conversor de Monedas");
        double cantidad = leerDouble("Ingrese la cantidad a convertir:");
        String monedaOrigen = leerString("Ingrese la moneda de origen:");
        String monedaDestino = leerString("Ingrese la moneda de destino:");

        double resultado = Monedas.convertirMoneda(cantidad, monedaOrigen, monedaDestino);
        if (resultado != -1) {
            System.out.println("La cantidad de " + cantidad + " " + monedaOrigen.toUpperCase() +
                    " equivale a " + resultado + " " + monedaDestino.toUpperCase());
        } else {
            System.out.println("No se pudo realizar la conversión. Por favor, revise las monedas ingresadas.");
        }
    }

    private static double leerDouble(String mensaje) {
        System.out.println(mensaje);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return Double.parseDouble(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido.");
            return leerDouble(mensaje);
        }
    }

    private static String leerString(String mensaje) {
        System.out.println(mensaje);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Error: No se pudo leer la entrada.");
            return "";
        }
    }
}
