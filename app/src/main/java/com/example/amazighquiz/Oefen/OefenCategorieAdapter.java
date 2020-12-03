//package com.example.amazighquiz.Oefen;
//
//import android.content.Intent;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.amazighquiz.R;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//
//public class OefenCategorieAdapter extends FirebaseRecyclerAdapter<Categorie, OefenCategorieAdapter.Viewholder> {
//
//    public OefenCategorieAdapter(@NonNull FirebaseRecyclerOptions<Categorie> options) {
//        super(options);
//    }
//
//    protected void onBindViewHolder(@NonNull OefenCategorieAdapter.Viewholder holder, int position, @NonNull Categorie model) {
//        holder.Categorie.setText(model.getCategorie());
//
//        Glide.with(holder.itemView.getContext()).load(model.getImage()).into(holder.Image);
//    }
//
//    public OefenCategorieAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_oefen_categorieen, parent, false);
//        return new OefenCategorieAdapter.Viewholder(view);
//    }
//
//    static class Viewholder extends RecyclerView.ViewHolder {
//        TextView Categorie, Button;
//        ImageView Image;
//
//        public Viewholder(@NonNull View itemView) {
//            super(itemView);
//            Categorie = itemView.findViewById(R.id.recyclerCategorie);
//            Button = itemView.findViewById(R.id.recyclerOefenen);
//            Image = itemView.findViewById(R.id.recyclerImage);
//        }
//    }
//}
