import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        Scanner scanner = null;

        try {
            while (true) {
                System.out.println("Enter the amount of bitcoins: ");
                userInput.useLocale(Locale.US);
                double numberOfBitcoin = userInput.nextDouble();

                URL url = new URL("https://blockchain.info/ticker");

                HttpURLConnection conn = getHttpURLConnection(url);

                StringBuilder jsonBuilder = readResponseBody(url);

                String inline = jsonBuilder.toString();

                JSONObject data_obj = new JSONObject(inline);

                JSONObject usdJsonObject = (JSONObject) data_obj.get("USD");
                JSONObject rubJsonObject = (JSONObject) data_obj.get("RUB");
                JSONObject eurJsonObject = (JSONObject) data_obj.get("EUR");
                double usdValue = usdJsonObject.getDouble("last");
                double rubValue = rubJsonObject.getLong("last");
                double eurValue = eurJsonObject.getDouble("last");
                System.out.println(numberOfBitcoin + " BTC = "
                        + multiplyTwoNumbers(numberOfBitcoin, usdValue) + " USD");
                System.out.println(numberOfBitcoin + " BTC = "
                        + multiplyTwoNumbers(numberOfBitcoin, rubValue) + " RUB");
                System.out.println(numberOfBitcoin + " BTC = "
                        + multiplyTwoNumbers(numberOfBitcoin, eurValue) + " EUR");
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
            if (scanner != null) {
                scanner.close();
            }
            userInput.close();
        }

    }


    public static HttpURLConnection getHttpURLConnection(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        return conn;
    }

    public static StringBuilder readResponseBody(URL url) throws IOException {
        Scanner scanner = new Scanner(url.openStream());
        StringBuilder jsonBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            jsonBuilder.append(scanner.nextLine());
        }
        return jsonBuilder;
    }

    public static double multiplyTwoNumbers(double numberOfBitcoin, double value) {
        return numberOfBitcoin * value;
    }
}
