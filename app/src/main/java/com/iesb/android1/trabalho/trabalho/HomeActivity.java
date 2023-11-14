package com.iesb.android1.trabalho.trabalho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.iesb.android1.aulaii.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private Button btLight_Home, btDark_Home, btSO_Home;
    private List<Contato> contatos = new ArrayList<>();

    private Contato contato;
    private RecyclerView recyclerViewHome;
    private ContatoAdapter contatoAdapter;
    private TextView tvWelcome;
    private Button btIncluir;
    private EditText etNome;
    private EditText etTel;
    private EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btDark_Home = findViewById(R.id.btDark_Home);
        btLight_Home = findViewById(R.id.btLight_Home);
        btSO_Home = findViewById(R.id.btSO_Home);

        tvWelcome = findViewById(R.id.tvWelcome);
        btIncluir = findViewById(R.id.btIncluir);

        Intent intent = getIntent();
        String welcomeReceived = intent.getStringExtra("KEY_NAME");

        tvWelcome.setText("Ola " + welcomeReceived);

        System.out.println("Nome: "+ welcomeReceived);

        etNome = findViewById(R.id.etNome_Home);
        etTel = findViewById(R.id.etTel);
        etEmail = findViewById(R.id.etEmail);

        recyclerViewHome = findViewById(R.id.recyclerViewHome);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(this));


        btIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Contato contato = new Contato();
                contato.setNome(etNome.getText().toString().trim());
                contato.setTelefone(etTel.getText().toString().trim());
                contato.setEmail(etEmail.getText().toString().trim());
                System.out.println("Contato  " + contato.toString());

                contatos.add(contato);

                contatoAdapter = new ContatoAdapter(contatos);
                recyclerViewHome.setAdapter(contatoAdapter);

                contatoAdapter.notifyItemInserted(contatos.size()-1);
                System.out.println("Contato  " + contatos.size());

                etNome.setText("");
                etTel.setText("");
                etEmail.setText("");

            }
        });

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

        btSO_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            }


        });




    }
}