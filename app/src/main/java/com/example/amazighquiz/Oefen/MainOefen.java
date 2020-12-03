package com.example.amazighquiz.Oefen;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.example.amazighquiz.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainOefen extends AppCompatActivity {

    private static final String EXTRA_TEXT = "com.example.amazighquiz.Oefen.EXTRA_TEXT";
    OefenAdapter adapter;
    DatabaseReference db;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_oefen);

        Intent intent = getIntent();
        String categorie = intent.getStringExtra(MainOefenCategorieen.EXTRA_TEXT);

        // database
        db = FirebaseDatabase.getInstance().getReference();

        viewPager2 = findViewById(R.id.recycler);

        FirebaseRecyclerOptions<Oefen> options
                = new FirebaseRecyclerOptions.Builder<Oefen>()
                .setQuery(db.child("Testen").child("Vragen").child(categorie), Oefen.class)
                .build();

        adapter = new OefenAdapter(options);
        viewPager2.setAdapter(adapter);
    }

    @Override protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}