package logic;

import java.io.Serializable;

public class Equipment implements Serializable {
    private String type; // e.g., Mountain, Winter, Summer
    private String description;

    // Constructor
    public Equipment(String type, String description) {
        this.type = type;
        this.description = description;
    }

    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
