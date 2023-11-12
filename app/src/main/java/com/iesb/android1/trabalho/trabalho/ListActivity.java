package com.iesb.android1.trabalho.trabalho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.iesb.android1.aulaii.R;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<Contato> contatos = new ArrayList<>();

    private RecyclerView recyclerView;

    private ContatoAdapter contatoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contatoAdapter = new ContatoAdapter(contatos);
        recyclerView.setAdapter(contatoAdapter);

        contatoAdapter.notifyItemInserted(contatos.size()-1);


        System.out.println("Contatos" + contatos.size());
    }
}