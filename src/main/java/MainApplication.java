import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

/**
 * Created by pwieczorek on 29.08.16.
 */
public class MainApplication {



    private static final String LINK_MAIN = "http://infoshareacademycom.2find.ru";
    private static final String LINK_BRAND = "/api/v2?lang=polish";
    private static JsonReader jrd;


    public static void main(String[] args) throws IOException, JSONException {

        //JSONObject json = jrd.readJsonFromUrl("http://infoshareacademycom.2find.ru/api/v2?lang=polish");
        JSONObject json = jrd.readJsonFromUrl(LINK_MAIN + LINK_BRAND);

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

        JSONObject jsonBrand = jrd.readJsonFromUrl(LINK_MAIN + brand1.getLink());

        HashMap<String,Model> modelMap = new HashMap<>();

        jArr = jsonBrand.getJSONArray("data");

        for (int i=0; i<jArr.length(); i++) {

            JSONObject jsonObject = jArr.getJSONObject(i);
            modelMap.put(jsonObject.getString("name"),new Model(jsonObject.getString("id"),jsonObject.getString("name"),jsonObject.getString("end_year"),
                    jsonObject.getString("end_month"),jsonObject.getString("start_year"),jsonObject.getString("start_month"),jsonObject.getString("vehicle_group"),
                    jsonObject.getString("link")));
        }

        System.out.println("Wybierz model:");
        Set<String> keyString = modelMap.keySet();

        for(String newString : keyString) {
            System.out.println(modelMap.get(newString).getName());
        }




    }
}
