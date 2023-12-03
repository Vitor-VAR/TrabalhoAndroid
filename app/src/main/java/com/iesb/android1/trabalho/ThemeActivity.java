package com.iesb.android1.trabalho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iesb.android1.aulaii.R;

public class ThemeActivity extends AppCompatActivity {

    private Button btLight_Home, btDark_Home, btSO_Home;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        btDark_Home = findViewById(R.id.btDark_Home);
        btLight_Home = findViewById(R.id.btLight_Home);
        btSO_Home = findViewById(R.id.btSO_Home);

        btDark_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }


        });

        btLight_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }


        });

        btDark_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            }


        });


    }
}