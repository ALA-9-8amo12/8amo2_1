package com.example.amazighquiz.Speel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.example.amazighquiz.Speel.MainSpeelCategorieen;
import com.example.amazighquiz.Speel.Speel;
import com.example.amazighquiz.Speel.SpeelAdapter;
import com.example.amazighquiz.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainSpeel extends AppCompatActivity {

    SpeelAdapter adapter;
    DatabaseReference db;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_speel);

        Intent intent = getIntent();
        String categorie = intent.getStringExtra(MainSpeelCategorieen.EXTRA_TEXT);

        // database
        db = FirebaseDatabase.getInstance().getReference();

        viewPager2 = findViewById(R.id.recycler);

        FirebaseRecyclerOptions<Speel> options
                = new FirebaseRecyclerOptions.Builder<Speel>()
                .setQuery(db.child("Testen").child("Vragen").child(categorie), Speel.class)
                .build();

        adapter = new SpeelAdapter(options);
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