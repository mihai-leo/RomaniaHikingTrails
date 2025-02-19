package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;

import logic.PointNormalizer;
import logic.Trail;


import java.util.ArrayList;
import java.util.List;

public class ViewTrail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewtrail);

        TrailMapView trailMapView = findViewById(R.id.customMapView);
        Trail trail = (Trail) getIntent().getSerializableExtra("trail");

        // Bind data to UI
        TextView trailName = findViewById(R.id.textViewTrailName);
        TextView trailDetails = findViewById(R.id.textViewTrailDetails);
        TextView info = findViewById(R.id.textViewInfo);

        if (trail != null) {
            trailName.setText(trail.getName());
            String messege="";
            if (trail.getEquipment()!=null)
            messege=trail.getEquipment().get(0).getType()+" :  "+trail.getEquipment().get(0).getDescription();
            trailDetails.setText(
                    "Difficulty: " + trail.getDifficulty() + "\n" +
                            "Distance: " + trail.getDistance() + " km\n" +
                            "Coordinates: " + trail.getLatitude() + ", " + trail.getLongitude()+"\n"+messege);



            List<float[]> points = null;
            if (trail.getTrailPath().size()>1)
            points= PointNormalizer.normalizePoints(trail.getTrailPathFull());

            // Set points on the custom map view
            if (points!=null)
                trailMapView.setPoints(points,trail.getDescriptions(),info);
        }
        // Example list of points (X, Y) coordinates between 0 and 1

    }
}

