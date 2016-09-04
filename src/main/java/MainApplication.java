import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by pwieczorek on 29.08.16.
 */
public class MainApplication {



    private static String linkMain = "http://infoshareacademycom.2find.ru";
    private static JsonReader jrd;


    public static void main(String[] args) throws IOException, JSONException {

        //JSONObject json = jrd.readJsonFromUrl("http://infoshareacademycom.2find.ru/api/v2?lang=polish");
        JSONObject json = jrd.readJsonFromUrl(linkMain +"/api/v2?lang=polish");

        HashMap<String, Brand> brandMap = new HashMap<>();

        JSONArray jArr = json.getJSONArray("data");

        Brand brand1 = new Brand();


        for (int i = 0; i < jArr.length(); i++) {

            JSONObject jsonObject = jArr.getJSONObject(i);
            brandMap.put(jsonObject.getString("name"),new Brand(jsonObject.getString("name"),jsonObject.getString("id"),jsonObject.getString("name_clear"),
            jsonObject.getString("link"),jsonObject.getBoolean("has_image")));
        }
        String inputName = new String("Nic");

        System.out.println("Podaj markę:");
        Scanner odczyt = new Scanner(System.in);

        inputName = odczyt.nextLine();

        if(brandMap.containsKey(inputName.toUpperCase())) {
            brand1 = brandMap.get(inputName.toUpperCase());
        } else {
            System.out.println("Podałeś złą markę!");
        }

        System.out.println(brand1.toString());


    }
}
