import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;
import java.util.Calendar;
import java.util.regex.Pattern;

import java.lang.*;
import java.lang.String;

/**
 * Created by pwieczorek on 29.08.16.
 */
public class Searcher {

    JsonReader jrd = new JsonReader();

    private static final String LINK_MAIN = "http://infoshareacademycom.2find.ru";
    private static final String LINK_BRAND = "/api/v2?lang=polish";

    static Calendar calendar = Calendar.getInstance();

    private TreeMap<String,Brand> getBrandMap(String link) throws IOException {

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

    private Brand getBrand(String inputName, TreeMap<String,Brand> brandMap) {
        Brand brand = new Brand();
        if(brandMap.containsKey(inputName.toUpperCase())) {
           brand = brandMap.get(inputName.toUpperCase());
        } else {
            System.out.println("Podałeś złą markę!");
            return new Brand();
        }
        return brand;
    }

    private TreeMap<String,Model> getModelMap(String link) throws IOException {

        JSONObject jsonBrand = jrd.readJsonFromUrl(LINK_MAIN + link);

        TreeMap<String, Model> modelMap = new TreeMap<>();

        JSONArray jArr = jsonBrand.getJSONArray("data");
        if(jArr.length() !=0) {
            for (int i = 0; i < jArr.length(); i++) {
                JSONObject jsonObject = jArr.getJSONObject(i);
                if (jsonObject != null) {
                    modelMap.put(
                            jsonObject.get("start_year").toString(),
                            new Model(
                                    jsonObject.getString("id"),
                                    jsonObject.getString("name"),
                                    jsonObject.optString("end_year",String.valueOf(calendar.get(Calendar.YEAR))),
                                    jsonObject.get("end_month").toString(),
                                    jsonObject.get("start_year").toString(),
                                    jsonObject.get("start_month").toString(),
                                    jsonObject.getString("vehicle_group"),
                                    jsonObject.getString("link")
                            )
                    );
                }
            }
        } else
        {
            System.out.println("Baza danych jest pusta!");
            System.exit(1);
        }
        return modelMap;
    }

    private TreeMap<String,Model> getModelMapByYear(TreeMap<String,Model> modelMap, String year) {
        TreeMap<String,Model> modelMapByYear = new TreeMap<>();
        Integer yearInt = 0;
        if(year.length() == 4) {
            yearInt = Integer.parseInt(year);
        } else {
            System.out.println("Podałeś zły rok!");
            System.exit(1);
        }

        for(String s : modelMap.keySet()) {
            Model model = modelMap.get(s);
            Integer startYear = Integer.parseInt(model.getStart_year());
            Integer endYear =0;
                endYear = Integer.parseInt(model.getEnd_year());


            if(yearInt >= startYear && yearInt <= endYear) {
                modelMapByYear.put(model.getId(), model);
            }
        }
        return modelMapByYear;
    }

    private TreeMap<String,Model> getModelMapByName(TreeMap<String,Model> modelMap, String name) {
        TreeMap<String,Model> modelMapByName = new TreeMap<>();
        Pattern pattern = Pattern.compile(name.toUpperCase());

        for(String s : modelMap.keySet()) {

        }

        return modelMapByName;
    }


    public void search() throws IOException, JSONException {



        TreeMap<String,Brand> brandMap = getBrandMap(LINK_MAIN + LINK_BRAND);

        Scanner odczyt = new Scanner(System.in);
        Brand brand;


        do {
            System.out.println("Podaj markę:");
            String inputName = odczyt.nextLine();

            brand = getBrand(inputName, brandMap);
        }  while(brand.getName() == null);

        TreeMap<String,Model> modelMap = getModelMap(brand.getLink());

        System.out.println("Podaj rok produkcji:");

        String inputYear = odczyt.nextLine();

        if(inputYear != null) {
            TreeMap<String, Model> modelMapByYear = getModelMapByYear(modelMap, inputYear);
            String endYear;
            for(String s : modelMapByYear.keySet()) {
                endYear = modelMapByYear.get(s).getEnd_year();
                if(endYear.equals(String.valueOf(calendar.get(Calendar.YEAR)))) {
                    endYear ="bd.";
                }

                System.out.println(modelMapByYear.get(s).getName() + ": " + modelMapByYear.get(s).getStart_year() + " - " + endYear);
            }
        }

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
