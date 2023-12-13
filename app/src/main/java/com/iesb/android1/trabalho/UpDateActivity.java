package com.iesb.android1.trabalho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.iesb.android1.aulaii.R;

public class UpDateActivity extends AppCompatActivity {

    EditText etNome_Update, etTel_Update, etEmail_Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_upade);

        etNome_Update = findViewById(R.id.etNome_Update);
        etTel_Update = findViewById(R.id.etTel_Update);
        etEmail_Update = findViewById(R.id.etEmail_Update);

        Intent intent = getIntent();
        String nameReceived = intent.getStringExtra("KEY_NAME");
        String phoneReceived = intent.getStringExtra("KEY_PHONE");
        String emailReceived = intent.getStringExtra("KEY_EMAIL");

        etNome_Update.setText(nameReceived);
        etTel_Update.setText(phoneReceived);
        etEmail_Update.setText(emailReceived);



    }
}