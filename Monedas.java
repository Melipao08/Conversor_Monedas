import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Monedas {
    private static final String API_KEY = "98543c798d6be8e3cc3e4d58";

    public static double convertirMoneda(double cantidad, String monedaOrigen, String monedaDestino) {
        Exchange exchange = obtenerTasasDeCambio(monedaOrigen);
        if (exchange != null) {
            Map<String, Double> tasasDeCambio = exchange.getConversion_rates();
            if (tasasDeCambio.containsKey(monedaDestino)) {
                double tasaDeCambio = tasasDeCambio.get(monedaDestino);
                return cantidad * tasaDeCambio;
            } else {
                System.out.println("La moneda de destino no está disponible en las tasas de cambio.");
            }
        }
        return -1; // Valor de retorno indicando un error
    }

    private static Exchange obtenerTasasDeCambio(String monedaOrigen) {
        String urlStr = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + monedaOrigen.toUpperCase();

        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                Gson gson = new Gson();
                return gson.fromJson(response.toString(), Exchange.class);
            } else {
                System.out.println("Error al realizar la solicitud. Código de respuesta: " + connection.getResponseCode());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
