package logic;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
public class Trail implements Serializable {
    private String name;
    private String difficulty; // Easy, Medium, Hard
    private double latitude;
    private double longitude;
    private double distance;
    private List<Point> trailPath;
    private List<WaterSource> waterSources;
    private List<Accommodation> accommodations;
    private List<Equipment> equipment;

    // Constructor
    public Trail(String name, String difficulty, double latitude, double longitude,
                 List<WaterSource> waterSources, List<Accommodation> accommodations, List<Equipment> equipment) {
        this.name = name;
        this.difficulty = difficulty;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance= 0.0;
        this.trailPath=new ArrayList<>();
        this.trailPath.add(new Point(latitude,longitude));
        this.waterSources = waterSources;
        this.accommodations = accommodations;
        this.equipment = equipment;
    }
    public Trail(String name, String difficulty, double latitude, double longitude) {
        this.name = name;
        this.difficulty = difficulty;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance=0.0;
        this.trailPath=new ArrayList<>();
        this.trailPath.add(new Point(latitude,longitude));
        this.waterSources = null;
        this.accommodations = null;
        this.equipment =  null;
    }
    // Getters and setters
    public String getName() {
        return name;
    }
    public double calculateDistance(double lat, double lon) {
        final int EARTH_RADIUS = 6371; // Radius of the Earth in kilometers

        double latDistance = Math.toRadians(lat - this.latitude);
        double lonDistance = Math.toRadians(lon - this.longitude);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(lat)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        this.distance= EARTH_RADIUS * c; // Distance in kilometers
        return this.distance;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    public List<Point> getTrailPath() {
        return trailPath;
    }
    public List<Point> getTrailPathFull() {
        List<Point> fullPath = new ArrayList<>();

        // Add trail path points
        if (trailPath != null) {
            fullPath.addAll(trailPath);
        }

        // Add water source points
        if (waterSources != null) {
            for (WaterSource waterSource : waterSources) {
                fullPath.add(new Point(waterSource.getLatitude(), waterSource.getLongitude(),1));
            }
        }

        // Add accommodation points
        if (accommodations != null) {
            for (Accommodation accommodation : accommodations) {
                fullPath.add(new Point(accommodation.getLatitude(), accommodation.getLongitude(),2));
            }
        }

        return fullPath;
    }
    public List<String> getDescriptions()
    {
        List<String> strings=new ArrayList<>();
        strings.add("Start Point"+"\nLocatio:"+trailPath.get(0).getLatitude()+","+trailPath.get(0).getLongitude());
        strings.add("End Point"+"\nLocatio:"+trailPath.get(trailPath.size()-1).getLatitude()+","+trailPath.get(trailPath.size()-1).getLongitude());
        if (waterSources != null) {
            for (WaterSource waterSource : waterSources) {
                strings.add("Water Source: "+waterSource.getName()+"\nLocation: "+waterSource.getLatitude()+","+waterSource.getLongitude()+" \nQuality: "+waterSource.getQuality());
            }
        }

        // Add accommodation points
        if (accommodations != null) {
            for (Accommodation accommodation : accommodations) {
                strings.add("Accomodation: "+accommodation.getName()+" \nLocation: "+accommodation.getLatitude()+","+accommodation.getLongitude()+" \nPrice: "+accommodation.getPrice()+" lei");
            }
        }
        return strings;
    }
    public void setTrailPath(List<Point> trailPath) {
        this.trailPath = trailPath;
    }

    public List<WaterSource> getWaterSources() {
        return waterSources;
    }

    public void setWaterSources(List<WaterSource> waterSources) {
        this.waterSources = waterSources;
    }

    public List<Accommodation> getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(List<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }
}
