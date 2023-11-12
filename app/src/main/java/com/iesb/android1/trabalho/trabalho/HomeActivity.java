package com.iesb.android1.trabalho.trabalho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.iesb.android1.aulaii.R;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWelcome;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvWelcome = findViewById(R.id.tvWelcome);

        Intent intent = getIntent();
        String welcomeReceived = intent.getStringExtra("KEY_NAME");

        tvWelcome.setText("Ola " + welcomeReceived);

        System.out.println("Nome: "+ welcomeReceived);

    }
}