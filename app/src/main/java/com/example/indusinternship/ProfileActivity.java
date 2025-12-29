package com.example.indusinternship;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    EditText name, email, contact, password, cnf_password;
    Button edit, update;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.profile_name);
        email = findViewById(R.id.profile_email);
        contact = findViewById(R.id.profile_contact);
        password = findViewById(R.id.profile_password);
        cnf_password = findViewById(R.id.profile_cnf_password);
        edit = findViewById(R.id.profile_edit);
        update = findViewById(R.id.profile_update);

        sp = getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);

        setData(false);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cnf_password.setVisibility(VISIBLE);
                update.setVisibility(VISIBLE);
                edit.setVisibility(GONE);

                setData(true);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cnf_password.setVisibility(GONE);
                update.setVisibility(GONE);
                edit.setVisibility(VISIBLE);

                setData(false);
            }
        });





    }

    private void setData(boolean b) {
        name.setText(sp.getString(ConstantSp.name, ""));
        email.setText(sp.getString(ConstantSp.email, ""));
        contact.setText(sp.getString(ConstantSp.contact,""));
        password.setText(sp.getString(ConstantSp.password, ""));

        name.setEnabled(b);
        email.setEnabled(b);
        contact.setEnabled(b);
        password.setEnabled(b);

    }
}