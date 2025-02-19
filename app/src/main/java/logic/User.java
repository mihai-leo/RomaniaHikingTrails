package logic;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String phone;
    private String email;
    private String userType; // "user", "admin", "addingUser"

    public User(String username, String password, String phone, String email, String userType) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.userType = userType;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
