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

    private static TreeMap<String,Brand> getBrandMap(String link) throws IOException {

        JSONObject json = jrd.readJsonFromUrl(link);
        TreeMap<String, Brand> brandMap = new TreeMap<String,Brand>();
        JSONArray jArr = json.getJSONArray("data");

        for (int i = 0; i < jArr.length(); i++) {

            JSONObject jsonObject = jArr.getJSONObject(i);
            if(jsonObject != null) {

                brandMap.put(jsonObject.getString("name"),new Brand(jsonObject));
            } else {
                System.out.println("Brak danych w bazie!");
                System.exit(1);
            }
        }
        return brandMap;
    }

    private static Brand getBrand(String inputName, TreeMap<String,Brand> brandMap) {
        Brand brand = new Brand();
        if(brandMap.containsKey(inputName.toUpperCase())) {
           brand = brandMap.get(inputName.toUpperCase());
        } else {
            System.out.println("Podałeś złą markę!");
            return new Brand();
        }
        return brand;
    }

    private static TreeMap<String,Model> getModelMap(String link) throws IOException {

        JSONObject jsonBrand = jrd.readJsonFromUrl(LINK_MAIN + link);

        TreeMap<String, Model> modelMap = new TreeMap<>();

        JSONArray jArr = jsonBrand.getJSONArray("data");

        for (int i=0; i<jArr.length(); i++) {
            JSONObject jsonObject = jArr.getJSONObject(i);

            if (jsonObject != null) {
                modelMap.put(jsonObject.getString("start_year"),new Model(jsonObject.getString("id"),jsonObject.getString("name"),jsonObject.get("end_year").toString(),
                        jsonObject.get("end_month").toString(),jsonObject.getString("start_year"),jsonObject.getString("start_month"),jsonObject.getString("vehicle_group"),
                        jsonObject.getString("link")));
            }
        }
        return modelMap;
    }

    private static TreeMap<String,Model> getModelMapByYear(TreeMap<String,Model> modelMap, String year) {
        TreeMap<String,Model> modelMapByYear = new TreeMap<>();
        Integer yearInt = Integer.parseInt(year);

        for(String s : modelMap.keySet()) {
            Integer startYear = Integer.parseInt(modelMap.get(s).getStart_year());
            Integer endYear = Integer.parseInt(modelMap.get(s).getEnd_year());

            if(yearInt >= startYear && yearInt <= endYear) {
                modelMapByYear.put(modelMap.get(s).getId(),modelMap.get(s));
            }
        }
        return modelMapByYear;
    }


    public static void main(String[] args) throws IOException, JSONException {



        TreeMap<String,Brand> brandMap = getBrandMap(LINK_MAIN + LINK_BRAND);

        Scanner odczyt = new Scanner(System.in);
        Brand brand = new Brand();

        do {
            System.out.println("Podaj markę:");
            String inputName = odczyt.nextLine();

            brand = getBrand(inputName, brandMap);
        }  while(brand.getName() == null);

        TreeMap<String,Model> modelMap = getModelMap(brand.getLink());

        System.out.println("Podaj rok produkcji:");




        /*if(odczyt.nextLine().toLowerCase().equals("lista")) {
            for (String newString : modelMap.keySet()) {
                System.out.println(modelMap.get(newString).getName());
            }
        }*/
        /*    String modelName = odczyt.nextLine();

        if(brandMap.containsKey(inputName.toUpperCase())) {
            brand = brandMap.get(inputName.toUpperCase());
        } else {
            System.out.println("Podałeś zły model!");
            System.exit(1);
        }*/





    }
}
