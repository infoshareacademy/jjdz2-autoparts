import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class Atena {

    public static final int SESSION_ERROR = -4;
    public static final int CODE_ERROR = -2;
    private final String KOD_DO_SESJI = "https://aztec.atena.pl/PWM2/rest/aztec/getbysession?sessionKey=";
    private final String KOD_KLIENTA = "&userKey=qY2?0Pw!";


    private String getLinkFromSessionCode(String code) {
        return KOD_DO_SESJI+code+KOD_KLIENTA;
    }

    private String getSessionCode() {
        Scanner keyboardReader = new Scanner(System.in);
        System.out.println("Podaj kod sesji:");
        return keyboardReader.nextLine();
    }

    private JSONObject getJSONFromLink(String Link) throws IOException {
        JsonReader jrd = new JsonReader();
        return jrd.readJsonFromUrl(Link);
    }

    private void jsonReader(JSONObject jObject) {
        JSONObject jsonObject = jObject.getJSONObject("Dane");
        Integer errorNr = jsonObject.getInt("Error");
        if(errorNr== SESSION_ERROR) {
            System.out.println("Podałeś zły klucz sesji!");
            System.exit(1);
        }else if(errorNr==CODE_ERROR){
            System.out.println("Podałeś zły kod użytkownika!");
            System.exit(1);
        }else {
            System.out.println("Rodzaj pojazdu: " +jsonObject.optString("Rodzaj_pojazdu","bd."));
            System.out.println("Marka: " +jsonObject.optString("D1","bd."));
            System.out.println("Model: " +jsonObject.optString("D5","bd."));
            System.out.println("Pojemność silnika: " +jsonObject.optString("P.1","bd."));
            System.out.println("Moc silnika: " +jsonObject.optString("P.2","bd."));
            System.out.println("Rodzaj paliwa: " +jsonObject.optString("P.3","bd."));
            System.out.println("Rok produkcji: " +jsonObject.optInt("Rok_produkcji",0));
        }


    }

    public void handleAtenaAztecReader() throws IOException {

        String SessionCode=getSessionCode();
        String link = getLinkFromSessionCode(SessionCode);
        JSONObject jsonObject = getJSONFromLink(link);
        jsonReader(jsonObject);
    }

}
