package com.example.indusinternship;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardActivity extends AppCompatActivity {

    Button profile, logout, delete;
    TextView username;

    SharedPreferences sp;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        db = openOrCreateDatabase("IndusInternship.db", MODE_PRIVATE, null);
        String createTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100), email VARCHAR(100), contact VARCHAR(10), password VARCHAR(20))";
        db.execSQL(createTable);


        sp = getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);

        username = findViewById(R.id.dashboard_username);
        profile = findViewById(R.id.dashboard_profile);
        logout = findViewById(R.id.dashboard_logout);
        delete = findViewById(R.id.dashboard_delete);

        username.setText("Welcome " + sp.getString(ConstantSp.name, ""));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();

                Toast.makeText(DashboardActivity.this, "Profile Logout", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deleteProfile = "DELETE FROM user WHERE email = '"+sp.getString(ConstantSp.email,"")+"'";
                db.execSQL(deleteProfile);

                Toast.makeText(DashboardActivity.this, "Profile Deleted, Successfully", Toast.LENGTH_SHORT).show();

                sp.edit().clear().commit();

                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });




    }
}