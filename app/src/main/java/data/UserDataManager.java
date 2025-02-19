package data;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import logic.User;

import java.io.Serializable;

public class UserDataManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "C:/Users/leomi/Documents/AC/3.1/SE/MyApplication5/app/src/main/java/data/users.json";

    private List<User> erorUsers()
    {
        List<User> users = new ArrayList<>();
        users.add(new User("doe1","123","9876543210","johndoe@example.com", "user"));
        users.add(new User("admin", "admin123", "1234567890", "admin@example.com", "admin"));
        users.add(new User("johndoe", "123", "9876543210", "johndoe@example.com", "user"));
        users.add(new User("janedoe", "welcome2023", "5551234567", "janedoe@example.com", "addingUser"));

        return users;
    }
    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return erorUsers();

            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            reader.close();

            JSONArray jsonArray = new JSONArray(json.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                users.add(new User(
                        obj.getString("username"),
                        obj.getString("password"),
                        obj.getString("phone"),
                        obj.getString("email"),
                        obj.getString("userType")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        users = new ArrayList<>();
        users.add(new User("johndoe","123","9876543210","johndoe@example.com", "user"));
        return users;
    }

    public void saveUsers(List<User> users) {
        try {
            JSONArray jsonArray = new JSONArray();
            for (User user : users) {
                JSONObject obj = new JSONObject();
                obj.put("username", user.getUsername());
                obj.put("password", user.getPassword());
                obj.put("phone", user.getPhone());
                obj.put("email", user.getEmail());
                obj.put("userType", user.getUserType());
                jsonArray.put(obj);
            }

            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write(jsonArray.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
