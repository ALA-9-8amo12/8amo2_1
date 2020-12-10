package com.example.amazighquiz.Speel;

import com.example.amazighquiz.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;


public class SpeelAdapter extends FirebaseRecyclerAdapter<Speel, SpeelAdapter.Viewholder> {

    public SpeelAdapter(@NonNull FirebaseRecyclerOptions<Speel> options) {
        super(options);
    }

    protected void onBindViewHolder(@NonNull com.example.amazighquiz.Speel.SpeelAdapter.Viewholder holder, int position, @NonNull Speel model) {
        holder.Categorie.setText(model.getCategorie());
        holder.Ned.setText(model.getNed());
        holder.Amazigh.setText(model.getAmazigh());
        holder.Vraag.setText(model.getVraag());

        Glide.with(holder.itemView.getContext()).load(model.getImage()).into(holder.Image);
    }

    public com.example.amazighquiz.Speel.SpeelAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_speel, parent, false);
        return new com.example.amazighquiz.Speel.SpeelAdapter.Viewholder(view);
    }

    static class Viewholder extends RecyclerView.ViewHolder {
        TextView Categorie, Ned, Amazigh, Vraag;
        ImageView Image;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            Categorie = itemView.findViewById(R.id.recyclerCategorie);
            Ned = itemView.findViewById(R.id.recyclerNed);
            Amazigh = itemView.findViewById(R.id.recyclerAmazigh);
            Vraag = itemView.findViewById(R.id.recyclerVraag);

            Image = itemView.findViewById(R.id.recyclerImage);
//          Geluid = itemView.findViewById(R.id.recyclerGeluid);
        }
    }
}