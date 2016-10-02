import java.util.Optional;

/**
 * Created by pwieczorek on 05.09.16.
 */
public class Model {

    private String id;
    private String name;
    private Optional<String> end_year;
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

    public Optional<String> getEnd_year() {
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

    public Model(String id, String name, Optional<String> end_year, String end_month, String start_year, String start_month, String vehicle_group, String link) {
        if(id != null) {
            this.id = id;
        }
        if(name != null) {
            this.name = name;
        }
        if(end_year != null) {
            this.end_year = end_year;
        }
        if(end_month != null) {
            this.end_month = end_month;
        }
        if(start_year != null) {
            this.start_year = start_year;
        }
        if(start_month != null) {
            this.start_month = start_month;
        }
        if(vehicle_group != null) {
            this.vehicle_group = vehicle_group;
        }
        if(link != null) {
            this.link = link;
        }
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
