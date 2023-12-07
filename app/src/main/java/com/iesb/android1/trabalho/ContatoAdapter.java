package com.iesb.android1.trabalho;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iesb.android1.aulaii.R;

import java.util.List;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ViewHolder> {

    private List<Person> contacts;
    private IOnItemClickListener listener;

    public interface IOnItemClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);
    }

    public ContatoAdapter(List<Person> contacts, IOnItemClickListener listener) {

        this.contacts = contacts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_name, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Person person = contacts.get(position);
        viewHolder.tvNome_Home.setText(person.getNome());
        viewHolder.tvTel_Home.setText(person.getTelefone());
        viewHolder.tvEmail_Home.setText(person.getEmail());

////        viewHolder.btEditar_Home.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View view) {
//////                Intent intent = new Intent(viewHolder.itemView.getContext(), UpDateActivity.class);
//////                intent.putExtra("Nome", String.valueOf(contacts.));
//////
//////
//////            }
//        });


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome_Home, tvTel_Home, tvEmail_Home;

        //LinearLayout view_Update;
        Button btEditar_Home;

        public ViewHolder(View itemview) {
            super(itemview);
            tvNome_Home = itemview.findViewById(R.id.tvNome_Home);
            tvTel_Home = itemview.findViewById(R.id.tvTel_Home);
            tvEmail_Home = itemview.findViewById(R.id.tvEmail_Home);
            //view_Update = itemview.findViewById(R.id.viewUpdate);
            btEditar_Home = itemview.findViewById(R.id.btEditar_Home);


        }
    }
}
