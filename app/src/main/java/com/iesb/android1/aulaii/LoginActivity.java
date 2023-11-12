package com.iesb.android1.aulaii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button btLogin;

    private EditText etLogin, etPassword;
    private TextView tvEmail, tvPassword;

    private String EMAIL = "vitor.ribeiro.var@gmail.com";

    private String PASSWORD = "1234";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Conectando a minha classe java aos componentes XML
        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        tvEmail = findViewById(R.id.tvEmail);
        tvPassword = findViewById(R.id.tvPassword);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(LoginActivity.this,"Nome: " + etLogin.getText() + "\nSenha: " + etPassword.getText(), Toast.LENGTH_SHORT).show();
//
//                tvEmail.setText(etLogin.getText());
//                tvPassword.setText(etPassword.getText());
                String inputLogin = etLogin.getText().toString().trim();
                String inputPassword = etPassword.getText().toString().trim();

                if(inputLogin.equals(EMAIL) && inputPassword.equals(PASSWORD)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("KEY_EMAIL", inputLogin);
                    intent.putExtra("KEY_NUMBER", 1234);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Credenciais inválidas", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

//    private Boolean login() {
//        return etLogin.getText().equals(EMAIL) && etPassword.getText().equals(PASSWORD);
//    };
}