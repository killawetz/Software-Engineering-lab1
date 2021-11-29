import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Enter the amount of bitcoins: ");
            int numberOfBitcoin = userInput.nextInt();

            URL url = new URL("https://blockchain.info/ticker");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();
            System.out.println(responsecode);

            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }



        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
