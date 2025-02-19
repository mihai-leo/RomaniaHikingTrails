package logic;

import java.io.Serializable;

public class Accommodation implements Serializable {
    private String name;
    private double latitude;
    private double longitude;
    private double price;

    // Constructor
    public Accommodation(String name, double latitude, double longitude, double price) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
