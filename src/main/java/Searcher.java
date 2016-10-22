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
                            jsonObject.get("id").toString(),
                            new Model(
                                    jsonObject.getString("id"),
                                    jsonObject.getString("name"),
                                    jsonObject.optString("end_year",String.valueOf(calendar.get(Calendar.YEAR))),
                                    jsonObject.optString("end_month","1"),
                                    jsonObject.optString("start_year","1900"),
                                    jsonObject.optString("start_month","1"),
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


        for(Model m : modelMap.values()) {

            if(m.getName().contains(name.toUpperCase())) {
                modelMapByName.put(m.getName().toUpperCase(),m);
            }
        }

        return modelMapByName;
    }

    private JSONObject getTypeJSON(String link) throws IOException {
        return jrd.readJsonFromUrl(LINK_MAIN+link);
    }

    private JSONArray getTypeArray(JSONObject TypeJSON, String arrayName) {
        return TypeJSON.optJSONArray(arrayName);
    }




    public void search() throws IOException, JSONException {



        TreeMap<String,Brand> brandMap = getBrandMap(LINK_MAIN + LINK_BRAND);

        Scanner odczyt = new Scanner(System.in);
        Brand brand;


        do {
            System.out.println("\nPodaj markę:");
            String inputName = odczyt.nextLine();

            brand = getBrand(inputName, brandMap);
        }  while(brand.getName() == null);

        TreeMap<String,Model> modelMap = getModelMap(brand.getLink());

        System.out.println("\nPodaj model:");
        String modelName = odczyt.nextLine();

        modelMap = getModelMapByName(modelMap,modelName);

        if(modelMap.keySet().isEmpty()) {
            System.out.println("Brak podanych modeli!");
            System.exit(1);
        }

        System.out.println("\nPodaj rok produkcji:");

        String inputYear = odczyt.nextLine();

        if(inputYear != null) {
            modelMap = getModelMapByYear(modelMap, inputYear);
        }

        if(modelMap.keySet().isEmpty()) {
            System.out.println("Brak danych w bazie!");
            System.exit(0);
        }

        TreeMap<String,Model> modelMapNumber = new TreeMap<>();

        String inputKlawisz;
        Model wybranyModel;

        if(modelMap.keySet().size()>1)
        {
            Integer i=1;
            System.out.println("\nWybierz jeden z podanych modeli:");
            for(String s : modelMap.keySet()) {
                System.out.println(i.toString()+". "+modelMap.get(s).getName());
                modelMapNumber.put(i.toString(),modelMap.get(s));
                i++;
            }
            inputKlawisz = odczyt.nextLine();

            while(!modelMapNumber.keySet().contains(inputKlawisz))
            {
                Integer j=1;
                System.out.println("\nWybrałeś zły model! Wybierz jeden z podanych modeli:");
                for(String s : modelMap.keySet()) {
                    System.out.println(j.toString()+". "+modelMap.get(s).getName());
                    j++;
                }
                inputKlawisz = odczyt.nextLine();
            }

            wybranyModel=modelMapNumber.get(inputKlawisz);
        }
        else {
            wybranyModel=modelMap.firstEntry().getValue();
        }

        JSONObject typeJSON = getTypeJSON(wybranyModel.getLink());

        JSONArray typeArray = getTypeArray(typeJSON,"data");

        List<String> engineList = new ArrayList<>();

        for(int i=0; i<typeArray.length(); i++) {
            if(!engineList.contains(typeArray.optJSONObject(i).getString("engine_txt"))) {
                engineList.add(typeArray.getJSONObject(i).getString("engine_txt"));
            }
        }

        System.out.println("\nWybierz rodzaj silnika:");

        for(Integer i=0; i<engineList.size(); i++) {
            Integer j=i+1;
            System.out.println(j.toString()+". "+engineList.get(i));
        }

        do {
            inputKlawisz=odczyt.nextLine();
        } while (inputKlawisz == null);

        Integer k=new Integer(inputKlawisz);

        String engineText = engineList.get(k-1);

        System.out.println(engineText);

        JSONArray nameArray = new JSONArray();

        for(int i=0; i<typeArray.length(); i++) {
            if(engineText.equals(typeArray.getJSONObject(i).getString("engine_txt"))) {
                nameArray.put(typeArray.getJSONObject(i));
            }
        }

        List<String> nameList = new ArrayList<>();

        for(int i=0; i<nameArray.length(); i++) {
            if(!nameList.contains(nameArray.getJSONObject(i).getString("name"))) {
                nameList.add(nameArray.getJSONObject(i).getString("name"));
            }
        }

        System.out.println("\nWybierz nazwę silnika:");

        for(Integer i=0; i<nameList.size(); i++) {
            Integer j=i+1;
            System.out.println(j.toString()+". "+nameList.get(i));
        }

        do {
            inputKlawisz=odczyt.nextLine();
        } while (inputKlawisz == null);

    }


}
