package com.example.amazighquiz.Speel;

import com.example.amazighquiz.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;


public class SpeelAdapter extends RecyclerView.Adapter<SpeelAdapter.SpeelViewHolder> {

    Context context;
    List<Speel> list;
    OnImageListener mOnImageListener;

    public SpeelAdapter(Context context, List<Speel> list, OnImageListener onImageListener) {
        this.context = context;
        this.list = list;
        this.mOnImageListener = onImageListener;
    }

    @NonNull
    @Override
    public SpeelAdapter.SpeelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_speel, parent, false);

        return new SpeelViewHolder(view, mOnImageListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeelAdapter.SpeelViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SpeelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        OnImageListener onImageListener;

        public SpeelViewHolder(@NonNull View itemView, OnImageListener onImageListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSpeel);
            this.onImageListener = onImageListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onImageListener.onImageClick(getAdapterPosition());
        }
    }

    public interface OnImageListener {
        void onImageClick(int position);
    }
}