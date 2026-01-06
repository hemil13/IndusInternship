package com.example.indusinternship;

import android.content.Intent;
import android.database.Cursor;
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

public class ForgetPasswordActivity extends AppCompatActivity {

    TextView email, password, cnf_password;
    Button update_password;

    String email_pattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        db = openOrCreateDatabase("IndusInternship.db", MODE_PRIVATE, null);
        String createTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100), email VARCHAR(100), contact VARCHAR(10), password VARCHAR(20))";
        db.execSQL(createTable);

        email = findViewById(R.id.forget_email);
        password = findViewById(R.id.forget_password);
        cnf_password = findViewById(R.id.forget__cnf_password);
        update_password = findViewById(R.id.forget_update_password);


        update_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().trim().equals("")){
                    email.setError("Email ID Required");
                }
                else if (!email.getText().toString().matches(email_pattern)) {
                    email.setError("Valid Email ID Required");
                }

                else if(password.getText().toString().trim().equals("")){
                    password.setError("Enter a Password");
                }
                else if(!cnf_password.getText().toString().trim().matches(password.getText().toString().trim())){
                    cnf_password.setError("Password doesn't match");
                }

                else{

                    String checkEmail = "SELECT * FROM user WHERE email = '"+email.getText().toString().trim()+"'";
                    Cursor emailCursor = db.rawQuery(checkEmail, null);

                    if(emailCursor.getCount() == 0){
                        Toast.makeText(ForgetPasswordActivity.this, "User Not Found", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgetPasswordActivity.this, SignupActivity.class);
                        startActivity(intent);
                    }
                    else{

                        String updatePassword = "UPDATE user SET password = '"+password.getText().toString().trim()+"' WHERE email = '"+email.getText().toString().trim()+"'";
                        db.execSQL(updatePassword);

                        Toast.makeText(ForgetPasswordActivity.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgetPasswordActivity.this, MainActivity.class);
                        startActivity(intent);
                    }





                }
            }
        });
    }
}