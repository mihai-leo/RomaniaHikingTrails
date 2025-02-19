package logic;

import java.io.Serializable;

public class WaterSource implements Serializable {
    private String name;
    private double latitude;
    private double longitude;
    private String quality;

    // Constructor
    public WaterSource(String name, double latitude, double longitude, String quality) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.quality = quality;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
}
