package logic;

import android.widget.Toast;
import data.TrailDataManager;
import data.UserDataManager;
import org.json.JSONObject;
import java.util.Collections;
import java.util.Comparator;
import java.io.Serializable;
import java.util.List;

public class AuthManager  implements Serializable {
    private static final long serialVersionUID = 1L;
    private UserDataManager userDataManager;
    private TrailDataManager trailDataManager;
    private List<User> users;
    private List<Trail> trails;
    private User activeUser;
    public AuthManager() {
        userDataManager = new UserDataManager();
        trailDataManager = new TrailDataManager();
        users = userDataManager.loadUsers();
        trails=trailDataManager.loadTrails();
        activeUser =null;
    }

    public User login(String username, String password) {
        if (users.isEmpty())
            users.add(new User("doe","123","9876543210","johndoe@example.com", "user"));
        users.add(new User("johndoe","123","9876543210","johndoe@example.com", "user"));

        for (User user : users) {

            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                activeUser=user;
                return user;
            }
        }
        return null; // User not found
    }
    public List<Trail> getTrails(double userLatitude,double userLongitude)
    {
        for (Trail trail : trails) {
            double distance = trail.calculateDistance(userLatitude, userLongitude);
            trail.setDistance(distance);
        }
        Collections.sort(trails, Comparator.comparingDouble(Trail::getDistance));
        return trails;
    }
    public int len()
    {
        return users.size();
    }

    public void register(User newUser) {
        users.add(newUser);
        userDataManager.saveUsers(users);
    }

}
