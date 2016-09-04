/**
 * Created by pwieczorek on 05.09.16.
 */
public class Model {

    private String id;
    private String name;
    private String end_year;
    private String end_month;
    private String start_year;
    private String start_month;
    private String vehicle_group;
    private String link;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEnd_year() {
        return end_year;
    }

    public String getEnd_month() {
        return end_month;
    }

    public String getStart_year() {
        return start_year;
    }

    public String getStart_month() {
        return start_month;
    }

    public String getVehicle_group() {
        return vehicle_group;
    }

    public String getLink() {
        return link;
    }

    public Model(String id, String name, String end_year, String end_month, String start_year, String start_month, String vehicle_group, String link) {
        this.id = id;
        this.name = name;
        this.end_year = end_year;
        this.end_month = end_month;
        this.start_year = start_year;
        this.start_month = start_month;
        this.vehicle_group = vehicle_group;
        this.link = link;
    }

    public Model() {

        id = null;
        name = null;
        end_year = null;
        end_month = null;
        start_year = null;
        start_month = null;
        vehicle_group = null;
        link = null;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", end_year='" + end_year + '\'' +
                ", end_month='" + end_month + '\'' +
                ", start_year='" + start_year + '\'' +
                ", start_month='" + start_month + '\'' +
                ", vehicle_group='" + vehicle_group + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
