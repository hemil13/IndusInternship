package com.example.indusinternship;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupActivity extends AppCompatActivity {
    EditText name, email, contact, password, cnfpassword;
    Button signup;

    String email_pattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.signup_name);
        email = findViewById(R.id.signup_email);
        contact = findViewById(R.id.signup_contact);
        password = findViewById(R.id.signup_password);
        cnfpassword = findViewById(R.id.signup_cnf_password);

        signup = findViewById(R.id.signup_button);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().trim().equals("")){
                    name.setError("Invalid Name");
                }
                if(!email.getText().toString().trim().matches(email_pattern)){
                    email.setError("Invalid Email");
                }
                if(contact.getText().toString().trim().equals("")){
                    contact.setError("Enter a contact number");
                }
                if(contact.getText().toString().trim().length()<10){
                    contact.setError("Invalid Contact Number");
                }
                if(password.getText().toString().trim().equals("")){
                    password.setError("Enter a Password");
                }
                if(password.getText().toString().trim().length()<8){
                    password.setError("Password must be 8 characters long");
                }
                if(!cnfpassword.getText().toString().trim().matches(password.getText().toString().trim())){
                    cnfpassword.setError("Password doesn't match");
                }
                else{
                    Toast.makeText(SignupActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}