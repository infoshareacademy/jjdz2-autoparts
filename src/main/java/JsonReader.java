import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pwieczorek on 29.08.16.
 */
public class JsonReader {


    private static Reader rd;

    private static String readAll(Reader rd) throws IOException {
        JsonReader.rd = rd;
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    /*public static void main(String[] args) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("http://infoshareacademycom.2find.ru/api/v2?lang=polish");
        System.out.println(json.toString());
    }*/
}
