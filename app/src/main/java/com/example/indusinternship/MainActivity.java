package com.example.indusinternship;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    TextView create_account;
    Button login;

    EditText email, password;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("IndusInternship.db", MODE_PRIVATE, null);
        String createTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100), email VARCHAR(100), contact VARCHAR(10), password VARCHAR(20))";
        db.execSQL(createTable);


        create_account = findViewById(R.id.create_new_account);
        login = findViewById(R.id.login_button);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);


        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String checkUser = "SELECT * FROM user WHERE email='"+email.getText().toString()+"' AND password='"+password.getText().toString()+"'";
                Cursor cursor = db.rawQuery(checkUser, null);

                if(cursor.getCount()>0){
//                Toast.makeText(MainActivity.this, "Login Succesfully", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
//                Snackbar.make(view, "Login Successfully", Snackbar.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}