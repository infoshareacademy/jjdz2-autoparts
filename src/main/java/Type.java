/**
 * Created by pwieczorek on 11.09.16.
 */
public class Type {

    private String id;
    private String model_id;
    private String brand_id;
    private String name;
    private String end_year;
    private String end_month;
    private String start_year;
    private String start_month;
    private String ccm;
    private String kw;
    private String hp;
    private String cylinders;
    private String engine;
    private String engine_txt;
    private String fuel;
    private String body;
    private String axle;
    private String max_weight;
    private String link;

    public Type(String link, String cylinders, String engine, String engine_txt, String fuel, String body, String axle,
                String max_weight, String end_month, String start_year, String start_month, String ccm, String kw,
                String hp, String model_id, String brand_id, String name, String end_year, String id) {
        this.link = link;
        this.cylinders = cylinders;
        this.engine = engine;
        this.engine_txt = engine_txt;
        this.fuel = fuel;
        this.body = body;
        this.axle = axle;
        this.max_weight = max_weight;
        this.end_month = end_month;
        this.start_year = start_year;
        this.start_month = start_month;
        this.ccm = ccm;
        this.kw = kw;
        this.hp = hp;
        this.model_id = model_id;
        this.brand_id = brand_id;
        this.name = name;
        this.end_year = end_year;
        this.id = id;



    }











}
