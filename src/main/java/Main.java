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

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                //Getting the response code
                int responsecode = conn.getResponseCode();
                System.out.println(responsecode);

                String inline = "";
                scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                JSONObject data_obj = new JSONObject(inline);

                JSONObject usdJsonObject = (JSONObject) data_obj.get("USD");
                JSONObject rubJsonObject = (JSONObject) data_obj.get("RUB");
                JSONObject eurJsonObject = (JSONObject) data_obj.get("EUR");
                double usdValue = usdJsonObject.getDouble("last");
                double rubValue = rubJsonObject.getLong("last");
                double eurValue = eurJsonObject.getDouble("last");
                System.out.println(numberOfBitcoin + " BTC = " + usdValue * numberOfBitcoin + " USD");
                System.out.println(numberOfBitcoin + " BTC = " + rubValue * numberOfBitcoin + " RUB");
                System.out.println(numberOfBitcoin + " BTC = " + eurValue * numberOfBitcoin + " EUR");
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
            if(scanner != null) {
                scanner.close();
            }
            userInput.close();
        }

    }
}
