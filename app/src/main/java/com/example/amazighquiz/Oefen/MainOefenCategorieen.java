package com.example.amazighquiz.Oefen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazighquiz.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainOefenCategorieen extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.amazighquiz.Oefen.EXTRA_TEXT";
    private RecyclerView recyclerView;
    private OefenCategorieAdapter adapter;
    DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_oefen_categorieen);

        db = FirebaseDatabase.getInstance().getReference();

        // define recycler in view
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Categorie> options
                = new FirebaseRecyclerOptions.Builder<Categorie>()
                .setQuery(db.child("Testen").child("Info"), Categorie.class)
                .build();

        adapter = new OefenCategorieAdapter(options);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OefenCategorieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String categorie) {
                Intent intent = new Intent(MainOefenCategorieen.this, MainOefen.class);
                intent.putExtra(EXTRA_TEXT, categorie);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}