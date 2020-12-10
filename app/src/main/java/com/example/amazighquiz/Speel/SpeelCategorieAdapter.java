package com.example.amazighquiz.Speel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amazighquiz.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class SpeelCategorieAdapter extends FirebaseRecyclerAdapter<Categorie, SpeelCategorieAdapter.Viewholder> {

    private SpeelCategorieAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position, String categorieName);
    }

    public void setOnItemClickListener(SpeelCategorieAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public SpeelCategorieAdapter(@NonNull FirebaseRecyclerOptions<Categorie> options) {
        super(options);
    }

    protected void onBindViewHolder(@NonNull SpeelCategorieAdapter.Viewholder holder, final int position, @NonNull Categorie model) {
        holder.Categorie.setText(model.getCategorie());

        Glide.with(holder.itemView.getContext()).load(model.getImage()).into(holder.Image);

    }

    public SpeelCategorieAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_speel_categorieen, parent, false);
        return new SpeelCategorieAdapter.Viewholder(view, mListener);
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        TextView Categorie;
        ImageView Image;

        public Viewholder(@NonNull View itemView, final SpeelCategorieAdapter.OnItemClickListener listener) {
            super(itemView);
            Categorie = itemView.findViewById(R.id.recyclerCategorie);
//            Button = itemView.findViewById(R.id.recyclerOefenen);
            Image = itemView.findViewById(R.id.recyclerImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        String categorieName = (String) Categorie.getText();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position, categorieName);
                        }
                    }
                }
            });
        }
    }
}
