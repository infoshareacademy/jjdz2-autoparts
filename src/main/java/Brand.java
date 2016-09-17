import org.json.JSONObject;

import java.util.Set;

/**
 * Created by pwieczorek on 04.09.16.
 */
public class Brand {
    private String name;
    private String id;
    private String name_clear;
    private String link;
    private boolean has_image;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getName_clear() {
        return name_clear;
    }

    public String getLink() {
        return link;
    }

    public boolean isHas_image() {
        return has_image;
    }

    private String has_imageToString() {
        if(isHas_image()){
            if (has_image){
                return "true";
            } else {
                return "false";
            }
        } else {
            return "false";
        }

    }

    public Brand(String name, String id, String name_clear, String link, boolean has_image) {
        this.name = name;
        this.id = id;
        this.name_clear = name_clear;
        this.link = link;
        this.has_image = has_image;
    }

    public Brand() {
        this.name = null;
        this.id = null;
        this.name_clear = null;
        this.link = null;
        this.has_image = false;
    }

    public Brand(JSONObject jObj) {
        Set<String> stringSet = jObj.keySet();
        if(stringSet.contains("name")) {
            this.name = jObj.getString("name");
        }
        else {
            this.name=null;
        }

        if(stringSet.contains("id")) {
            this.id = jObj.getString("id");
        }
        else {
            this.id=null;
        }

        if(stringSet.contains("name_clear")) {
            this.name_clear = jObj.getString("name_clear");
        }
        else {
            this.name_clear=null;
        }

        if(stringSet.contains("link")) {
            this.link = jObj.getString("link");
        }
        else {
            this.link=null;
        }

        if(stringSet.contains("has_image")) {
            this.has_image = jObj.getBoolean("has_image");
        }
        else {
            this.has_image=false;
        }
    }

    @Override
    public String toString() {
        return "name="+name+", name_clear="+name_clear+", id="+id+", link="+link+", has_image="+has_imageToString();
    }
}
