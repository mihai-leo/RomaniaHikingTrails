package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUserName, editTextEmail, editTextPhone, editTextPassword;
    private Spinner spinnerUserType;
    private Button buttonRegister, buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // Initialize Views
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        spinnerUserType = findViewById(R.id.spinnerUserType);
        buttonRegister = findViewById(R.id.buttonRegister);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUserType.setAdapter(adapter);
        // Set button click listeners
        buttonRegister.setOnClickListener(v -> registerUser());

    }

    private void registerUser() {
        // Get input data
        String userName = editTextUserName.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = editTextPhone.getText().toString();
        String password = editTextPassword.getText().toString();
        String userType = spinnerUserType.getSelectedItem().toString();

        // Basic validation (can be expanded)
        if (userName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Registration logic goes here (e.g., store data in database or backend)
        // For now, show a success message
        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();

        // Optionally, navigate to the main activity or login page
        finish(); // Close the registration activity
    }
}
