package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import logic.AuthManager;
import logic.Trail;
import logic.User;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   // private AuthManager authManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        AuthManager authManager = new AuthManager();
        System.out.println("dsadas");
        EditText usernameInput = findViewById(R.id.editTextText);
        EditText passwordInput = findViewById(R.id.editTextTextPassword);
        Button loginButton = findViewById(R.id.button);
        Button registerButton = findViewById(R.id.button2);

        loginButton.setOnClickListener(view -> {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();
         //   Toast.makeText(this, "Welcome " , Toast.LENGTH_SHORT).show();
            System.out.println("dsadas");
            User user = authManager.login(username, password);
            System.out.println();

            if (user != null) {
                Toast.makeText(this, "Welcome " + user.getUsername(), Toast.LENGTH_SHORT).show();
                // Navigate to another activity
                // Create an Intent to navigate to MapsActivity
                Intent intent = new Intent(this, MapsActivity.class);

                // Attach the User object
                List<Trail> trails=authManager.getTrails(40.7128,-74.0060);
                intent.putExtra("loggedInUser", (Serializable) trails);

                // Start the activity
                startActivity(intent);
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
          //  Toast.makeText(this, authManager.len() , Toast.LENGTH_SHORT).show();
        });

        registerButton.setOnClickListener(view -> {
            // Navigate to registration page (not implemented in this snippet)
//            Toast.makeText(this, "Registration screen not implemented yet", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
