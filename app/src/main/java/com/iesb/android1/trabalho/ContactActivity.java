package com.iesb.android1.trabalho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iesb.android1.aulaii.R;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity implements ContatoAdapter.IOnItemClickListener {

    private Button btLight_Home, btDark_Home, btSO_Home, btIncluir_Home, btEditar_Home, btExcluir_Home;
    private List<Person> contactsList, personList;
    private Person person1;
    private RecyclerView recyclerViewHome;
    private ContatoAdapter contatoAdapter;
    private TextView tvWelcome;
    private EditText etNome, etTel, etEmail;
    private PersonDAO personDAO;

    private long retorno;

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        tvWelcome = findViewById(R.id.tvWelcome);

        Intent intent = getIntent();
        String welcomeReceived = intent.getStringExtra("KEY_NAME");

        tvWelcome.setText(" " + welcomeReceived);

        System.out.println("Nome: " + welcomeReceived);
//O código acima refere-se a view welcome

        //Botões de alteração do tema da tela Home
        btDark_Home = findViewById(R.id.btDark_Home);
        btLight_Home = findViewById(R.id.btLight_Home);
        btSO_Home = findViewById(R.id.btSO_Home);


        recyclerViewHome = findViewById(R.id.recyclerViewHome);
        etNome = findViewById(R.id.etNome_Home);
        etTel = findViewById(R.id.etTel);
        etEmail = findViewById(R.id.etEmail);
        btIncluir_Home = findViewById(R.id.btIncluir_Home);

        personDAO = new PersonDAO(this);
        contactsList = new ArrayList<>();
        contatoAdapter = new ContatoAdapter(contactsList, this);

        recyclerViewHome.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHome.setAdapter(contatoAdapter);


        btIncluir_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = etNome.getText().toString().trim();
                String tel = etTel.getText().toString().trim();
                String email = etEmail.getText().toString().trim();

                person1 = new Person(nome, tel, email);
                retorno = personDAO.addPerson(person1);
                System.out.println("linhas afetadas " + retorno);

                if(retorno != -1){
                    alerta("Sucesso!");
                    System.out.println("Contato salvo no banco com sucesso!");
                    System.out.println("Contato  " + person1.toString());

                    contactsList.add(person1);
                    updateRecyclerView();

                    contatoAdapter.notifyItemInserted(contactsList.size() - 1);

                    System.out.println("Contato  " + contactsList.size());

                    //Limpa o formulário ao incluir contato
                    etNome.setText("");
                    etTel.setText("");
                    etEmail.setText("");

                }else{
                    alerta("Erro ao interagir com DB");
                    System.out.println("Erro ao interagir com o banco!");}


            }
        });

        if(!contactsList.isEmpty()){
            btEditar_Home = findViewById(R.id.btEditar_Home);
            btExcluir_Home = findViewById(R.id.btExcluir_Home);

            btEditar_Home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView tvNome_Home, tvTel_Home, tvEmail_Home;

                    tvNome_Home = findViewById(R.id.tvNome_Home);
                    tvTel_Home = findViewById(R.id.tvTel_Home);
                    tvEmail_Home = findViewById(R.id.tvEmail_Home);

                    String inputNome = tvNome_Home.getText().toString().trim();
                    String inputTel = tvTel_Home.getText().toString().trim();
                    String inputEmail = tvEmail_Home.getText().toString().trim();


                    Intent intent = new Intent( ContactActivity.this, UpDateActivity.class);
                    intent.putExtra("KEY_NAME", inputNome);
                    intent.putExtra("KEY_PHONE ", inputTel);
                    intent.putExtra("KEY_EMAIL", inputEmail);
                    startActivity(intent);
                    finish();

                }
            });



            updateRecyclerView();

        }

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

    private void updateRecyclerView() {
        contactsList.clear();
        contactsList.addAll(personDAO.getAllPerson());
        contatoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {

    }

    private void alerta(String menssagem){

        Toast.makeText(this, menssagem, Toast.LENGTH_SHORT).show();
    }
}