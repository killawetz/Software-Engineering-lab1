import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainTest {

    @Test
    public void isAPIWorking() {
        int actualResponseCode = 0;
        try {
            URL url = new URL("https://blockchain.info/ticker");
            actualResponseCode = Main.getHttpURLConnection(url).getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int expectedResponseCode = 200;
        Assert.assertEquals(expectedResponseCode, actualResponseCode);
    }


    @Test
    public void isResponseBodyNotNull() {
        String responseJson = null;
        try {
            URL url = new URL("https://blockchain.info/ticker");
             responseJson = Main.readResponseBody(url).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(responseJson);
    }


    @Test
    public void isResponseBodyValid() {
        String responseJson = "";
        try {
            URL url = new URL("https://blockchain.info/ticker");
            responseJson = Main.readResponseBody(url).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(responseJson.length() > 0);
    }

    @Test
    public void testMultiply() {
        double number1 = 10.0;
        double number2 = 12.0;
        double actualResult = Main.multiplyTwoNumbers(number1, number2);
        Assert.assertEquals(number1 * number2, actualResult, 0);
    }

}
