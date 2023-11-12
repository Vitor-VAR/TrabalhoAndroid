package com.iesb.android1.trabalho.trabalho;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iesb.android1.aulaii.R;

import java.util.List;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.NameViewHolder> {

    private List<Contato> contatos;

    public ContatoAdapter(List<Contato>contatos){

        this.contatos = contatos;

    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_name, parent, false);
        return new NameViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder nameViewHolder, int position) {
        Contato contato = contatos.get(position);

        nameViewHolder.tvNome.setText(contato.getNome());
        nameViewHolder.tvTelefone.setText(contato.getTelefone());
        nameViewHolder.tvEmail.setText(contato.getEmail());

    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }

    class NameViewHolder extends RecyclerView.ViewHolder{

        TextView tvNome;
        TextView tvTelefone;
        TextView tvEmail;


        NameViewHolder (View view){

            super(view);
            tvNome = view.findViewById(R.id.tvNome);
            tvTelefone = view.findViewById(R.id.tvTelefone);
            tvEmail = view.findViewById(R.id.tvEmail);



        }
    }
}
