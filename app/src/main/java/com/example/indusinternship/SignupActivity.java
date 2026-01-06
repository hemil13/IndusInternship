package com.example.indusinternship;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupActivity extends AppCompatActivity {
    EditText name, email, contact, password, cnfpassword;
    Button signup;
    TextView already_account;

    String email_pattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";

    SharedPreferences sp;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = openOrCreateDatabase("IndusInternship.db", MODE_PRIVATE, null);
        String createTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100), email VARCHAR(100), contact VARCHAR(10), password VARCHAR(20))";
        db.execSQL(createTable);

        name = findViewById(R.id.signup_name);
        email = findViewById(R.id.signup_email);
        contact = findViewById(R.id.signup_contact);
        password = findViewById(R.id.signup_password);
        cnfpassword = findViewById(R.id.signup_cnf_password);
        already_account = findViewById(R.id.already_account);

        signup = findViewById(R.id.signup_button);

        sp = getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);

        already_account.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                   startActivity(intent);
               }
           }
        );


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().trim().equals("")){
                    name.setError("Invalid Name");
                }
                else if(!email.getText().toString().trim().matches(email_pattern)){
                    email.setError("Invalid Email");
                }
                else if(contact.getText().toString().trim().equals("")){
                    contact.setError("Enter a contact number");
                }
                else if(contact.getText().toString().trim().length()<10){
                    contact.setError("Invalid Contact Number");
                }
                else if(password.getText().toString().trim().equals("")){
                    password.setError("Enter a Password");
                }
                else if(!cnfpassword.getText().toString().trim().matches(password.getText().toString().trim())){
                    cnfpassword.setError("Password doesn't match");
                }
                else{

                    String checkUser = "SELECT * FROM user WHERE email='"+email.getText().toString()+"' OR contact='"+contact.getText().toString()+"'";
                    Cursor cursor = db.rawQuery(checkUser, null);

                    if(cursor.getCount()>0){
                        Toast.makeText(SignupActivity.this, "User Already Exist", Toast.LENGTH_SHORT).show();
//                        return;
                    }
                    else {
                        String insertUser = "INSERT INTO user VALUES(NULL,'"+name.getText().toString()+"','"+email.getText().toString()+"','"+contact.getText().toString()+"','"+password.getText().toString()+"')";
                        db.execSQL(insertUser);


                        sp.edit().putString(ConstantSp.name, name.getText().toString()).commit();
                        sp.edit().putString(ConstantSp.email, email.getText().toString()).commit();
                        sp.edit().putString(ConstantSp.contact, contact.getText().toString()).commit();
                        sp.edit().putString(ConstantSp.password, password.getText().toString()).commit();

                        Toast.makeText(SignupActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignupActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }




                }
            }
        });
    }
}