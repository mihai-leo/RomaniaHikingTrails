package logic;

import java.io.Serializable;

public class Point implements Serializable {
    private double latitude;
    private double longitude;
    private double type;

    // Constructor
    public Point(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.type= 0.0f;
    }
    public Point(double latitude, double longitude,double type) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.type= type;
    }
    // Getters and setters
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

    public double getType() {
        return type;
    }

    public void setType(double type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Point{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

}
