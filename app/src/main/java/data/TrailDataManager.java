package data;

import android.media.audiofx.DynamicsProcessing;
import com.example.myapplication.L;
import logic.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

public class TrailDataManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "C:/Users/leomi/Documents/AC/3.1/SE/MyApplication5/app/src/main/java/data/trails.json";

    private List<Trail> errorTrails() {
        List<Trail> trails = new ArrayList<>();
        Trail trail=new Trail("Full Trail","Easy",47.516973, 24.197790);
        trail.setTrailPath(demoPoints(trail.getLatitude(),trail.getLongitude()));
        trail.setEquipment(demoEquipment());
        trail.setWaterSources(demoWater());
        trail.setAccommodations(demoAccommodation());
        trails.add(trail);
        trails.add(new Trail("Sunny Peaks", "Easy", 45.1234, -93.1234));
        trails.add(new Trail("Rocky Path", "Medium", 46.5678, -94.5678));
        trails.add(new Trail("Forest Trek", "Hard", 47.9101, -95.9101));
        return trails;
    }

    public List<Trail> loadTrails() {
        List<Trail> trails = new ArrayList<>();
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return errorTrails();

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
                Trail trail = new Trail(
                        obj.getString("name"),
                        obj.getString("difficulty"),
                        obj.getDouble("latitude"),
                        obj.getDouble("longitude")
                );

                // Optionally handle water sources, accommodations, and equipment lists
                JSONArray waterSourcesArray = obj.optJSONArray("waterSources");
                if (waterSourcesArray != null) {
                    for (int j = 0; j < waterSourcesArray.length(); j++) {
                        //trail.getWaterSources().add(waterSourcesArray.getString(j));
                    }
                }

                JSONArray accommodationsArray = obj.optJSONArray("accommodations");
                if (accommodationsArray != null) {
                    for (int j = 0; j < accommodationsArray.length(); j++) {
                       // trail.getAccommodations().add(accommodationsArray.getString(j));
                    }
                }

                JSONArray equipmentArray = obj.optJSONArray("equipment");
                if (equipmentArray != null) {
                    for (int j = 0; j < equipmentArray.length(); j++) {
                        //trail.getEquipment().add(equipmentArray.getString(j));
                    }
                }

                trails.add(trail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trails.isEmpty() ? errorTrails() : trails;
    }
    public List<Point> demoPoints(double lat,double log){
        List<Point> demoPoints= new ArrayList<>();
        demoPoints.add(new Point(lat,log));
        demoPoints.add(new Point(47.517039, 24.200747));
        demoPoints.add(new Point(47.517106, 24.203705));
        demoPoints.add(new Point(47.517173, 24.206663));
        demoPoints.add(new Point(47.517239, 24.209771));
        demoPoints.add(new Point(47.516634, 24.213101));
        demoPoints.add(new Point(47.515968, 24.216432));
        demoPoints.add(new Point(47.514866, 24.219763));
        demoPoints.add(new Point(47.513564, 24.223093));
        demoPoints.add(new Point(47.512899, 24.228093));
        demoPoints.add(new Point(47.514058, 24.230569));
        demoPoints.add(new Point(47.515217, 24.233045));
        demoPoints.add(new Point(47.516376, 24.235520));
        demoPoints.add(new Point(47.517035, 24.237994));
        demoPoints.add(new Point(47.518102, 24.239063));
        demoPoints.add(new Point(47.519169, 24.240133));
        demoPoints.add(new Point(47.520236, 24.241202));
        demoPoints.add(new Point(47.521240, 24.243200));
        demoPoints.add(new Point(47.521684, 24.244438));
        demoPoints.add(new Point(47.522129, 24.245675));
        demoPoints.add(new Point(47.516634, 24.213101));
        demoPoints.add(new Point(47.522550, 24.248916));
        demoPoints.add(new Point(47.522918, 24.250427));
        demoPoints.add(new Point(47.523286, 24.251937));
        demoPoints.add(new Point(47.523653, 24.253448));
        demoPoints.add(new Point(47.524020, 24.253761));
        demoPoints.add(new Point(47.524387, 24.254074));
        demoPoints.add(new Point(47.525570, 24.253230));
        demoPoints.add(new Point(47.526754, 24.252386));

        return demoPoints;
    }
    public void saveTrails(List<Trail> trails) {
        try {
            JSONArray jsonArray = new JSONArray();
            for (Trail trail : trails) {
                JSONObject obj = new JSONObject();
                obj.put("name", trail.getName());
                obj.put("difficulty", trail.getDifficulty());
                obj.put("latitude", trail.getLatitude());
                obj.put("longitude", trail.getLongitude());

                JSONArray waterSourcesArray = new JSONArray(trail.getWaterSources());
                obj.put("waterSources", waterSourcesArray);

                JSONArray accommodationsArray = new JSONArray(trail.getAccommodations());
                obj.put("accommodations", accommodationsArray);

                JSONArray equipmentArray = new JSONArray(trail.getEquipment());
                obj.put("equipment", equipmentArray);

                jsonArray.put(obj);
            }

            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write(jsonArray.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Equipment> demoEquipment()
    {
        List<Equipment> list = new ArrayList<>();
        list.add(new Equipment("Summer equipment","Soft clothes with good ventilation, and an impermeable jacket"));
        return list;
    }
    public List<Accommodation> demoAccommodation()
    {
        List<Accommodation> list = new ArrayList<>();
        list.add(new Accommodation("Camping Ground",47.521169, 24.241133,20));
        return list;
    }
    public List<WaterSource> demoWater()
    {
        List<WaterSource> list= new ArrayList<>();
        list.add(new WaterSource("Blue Spring",47.516634, 24.220101,"Good"));
        list.add(new WaterSource("Izvorul Rece",47.519634, 24.233101,"Good"));

        return list;
    }
}
