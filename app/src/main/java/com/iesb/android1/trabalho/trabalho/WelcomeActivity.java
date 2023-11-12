package com.iesb.android1.trabalho.trabalho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.iesb.android1.aulaii.R;

public class WelcomeActivity extends AppCompatActivity {

    private Button btEntrar;

    private EditText etNome;

    private TextView tvWelcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        etNome = findViewById(R.id.etNome);
        btEntrar = findViewById(R.id.btEntrar);
        tvWelcome = findViewById(R.id.tvWelcome);


        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputNome = etNome.getText().toString().trim();
                Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                intent.putExtra("KEY_NAME", inputNome);
                startActivity(intent);
                finish();


            }
        });




    }
}