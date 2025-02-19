package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.TrailAdapter;
import logic.Trail;

public class MapsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TrailAdapter adapter;
    private List<Trail> allTrails; // List containing all trails
    private List<Trail> filteredTrails; // List to hold filtered trails

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);

        allTrails = (List<Trail>) getIntent().getSerializableExtra("loggedInUser");
        if (allTrails == null) {
            Toast.makeText(this, "Invalid user or trails not loaded", Toast.LENGTH_SHORT).show();
            return;
        }

        recyclerView = findViewById(R.id.recyclerViewTrails);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        filteredTrails = new ArrayList<>(allTrails); // Initially display all trails
        adapter = new TrailAdapter(filteredTrails,this);
        recyclerView.setAdapter(adapter);

        setupFilters();
    }

    private void setupFilters() {
        // Difficulty Filter
        Spinner difficultySpinner = findViewById(R.id.spinnerDifficulty);
        ArrayAdapter<CharSequence> difficultyAdapter = ArrayAdapter.createFromResource(this,
                R.array.difficulty_levels, android.R.layout.simple_spinner_item);
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(difficultyAdapter);



        // Set listeners for filters
        difficultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterTrails();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void filterTrails() {
        Spinner difficultySpinner = findViewById(R.id.spinnerDifficulty);

        String selectedDifficulty = difficultySpinner.getSelectedItem().toString();

        filteredTrails.clear();
        for (Trail trail : allTrails) {
            boolean matchesDifficulty = selectedDifficulty.equals("All") || trail.getDifficulty().equalsIgnoreCase(selectedDifficulty);
            boolean matchesDistance = true; // Default to true if no filtering by distance is applied



            if (matchesDifficulty ) {
                filteredTrails.add(trail);
            }
        }

        adapter.notifyDataSetChanged(); // Update the RecyclerView
    }
}
