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

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                JSONObject data_obj = new JSONObject(inline);

                //Get the required data using its key
                JSONObject jsonObject1 = (JSONObject) data_obj.get("USD");
                double btcValue = jsonObject1.getDouble("last");
                System.out.println(numberOfBitcoin + " BTC = " + btcValue * numberOfBitcoin + " USD");
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
