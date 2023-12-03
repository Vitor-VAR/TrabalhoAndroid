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

        nameViewHolder.tvNome_Home.setText(contato.getNome());
        nameViewHolder.tvTel_Home.setText(contato.getTelefone());
        nameViewHolder.tvEmail_Home.setText(contato.getEmail());

    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }

    class NameViewHolder extends RecyclerView.ViewHolder{

        TextView tvNome_Home;
        TextView tvTel_Home;
        TextView tvEmail_Home;

        NameViewHolder (View view){

            super(view);
            tvNome_Home = view.findViewById(R.id.tvNome_Home);
            tvTel_Home = view.findViewById(R.id.tvTel_Home);
            tvEmail_Home = view.findViewById(R.id.tvEmail_Home);



        }
    }
}
