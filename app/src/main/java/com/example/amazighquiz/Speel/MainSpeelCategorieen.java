package com.example.amazighquiz.Speel;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazighquiz.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainSpeelCategorieen extends AppCompatActivity {


    public static final String EXTRA_TEXT = "com.example.amazighquiz.Speel.EXTRA_TEXT";
    private RecyclerView recyclerView;
    private SpeelCategorieAdapter adapter;
    DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_speel_categorieen);


        db = FirebaseDatabase.getInstance().getReference();

        // define recycler in view
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<com.example.amazighquiz.Speel.Categorie> options
                = new FirebaseRecyclerOptions.Builder<com.example.amazighquiz.Speel.Categorie>()
                .setQuery(db.child("Testen").child("Info"), Categorie.class)
                .build();

        adapter = new SpeelCategorieAdapter(options);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SpeelCategorieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String categorie) {
                Intent intent = new Intent(MainSpeelCategorieen.this, MainSpeel.class);
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