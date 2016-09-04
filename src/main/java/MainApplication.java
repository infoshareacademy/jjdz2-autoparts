import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by pwieczorek on 29.08.16.
 */
public class MainApplication {

    private static String linkMain = "http://infoshareacademycom.2find.ru";
    private static JsonReader jrd;


    public static void main(String[] args) throws IOException, JSONException {

        //JSONObject json = jrd.readJsonFromUrl("http://infoshareacademycom.2find.ru/api/v2?lang=polish");
        JSONObject json = jrd.readJsonFromUrl(linkMain +"/api/v2?lang=polish");

        JSONArray jarr = json.getJSONArray("data");

        System.out.println(jarr.toString());

        //String text_strony = json.toString();

        //System.out.println("");
    }
}
