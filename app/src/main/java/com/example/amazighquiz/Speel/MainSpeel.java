package com.example.amazighquiz.Speel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amazighquiz.Speel.MainSpeelCategorieen;
import com.example.amazighquiz.Speel.Speel;
import com.example.amazighquiz.Speel.SpeelAdapter;
import com.example.amazighquiz.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainSpeel extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    ImageView imageView;
    SpeelAdapter adapter;
    DatabaseReference db;
    RecyclerView recyclerView;
    String categorie;
    List<Speel> speelList;
    List<Speel> antwoordList;
    List<Integer> repNumb;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_speel);

        Intent intent = getIntent();
        categorie = intent.getStringExtra(MainSpeelCategorieen.EXTRA_TEXT);

        // database
        db = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(MainSpeel.this, 2));
        speelList = new ArrayList<>();

//        FirebaseRecyclerOptions<Speel> options
//                = new FirebaseRecyclerOptions.Builder<Speel>()
//                .setQuery(db.child("Testen").child("Vragen").child(categorie), Speel.class)
//                .build();
//

        getFirebaseData();

    }

    public void getFirebaseData() {
        Query query = db.child("Testen").child("Vragen").child(categorie);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Speel speel = new Speel();

                        speel.setImage(snapshot.child("Image").getValue(String.class));
                        speel.setGeluid(snapshot.child("Geluid").getValue(String.class));

                        speelList.add(speel);
                    }

                    startAdapter();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void startAdapter() {
        antwoordList = new ArrayList<>();
        repNumb = new ArrayList();
        int rGetal;
        Boolean repBool = false;

        antwoordList.add(speelList.get(count));
        repNumb.add(count);

        for (int i = 1; i < 6; i++) {
            rGetal = new Random().nextInt(speelList.size());

            for (int x = 0; x < repNumb.size(); x++) {

                if (repNumb.get(x).equals(rGetal)) {

                    repBool = true;
                    break;
                } else {

                    repBool = false;
                }
            }

            if (!repBool) {
                repNumb.add(rGetal);
                antwoordList.add(speelList.get(rGetal));

            } else {
                i--;
            }
        }

        Collections.shuffle(antwoordList);

        adapter = new SpeelAdapter(getApplicationContext(), antwoordList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }


//    public void onClick() {
//        Log.d(TAG, "onClick: werkt");
//        count++;
//        startAdapter();
//    }
//    @Override protected void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override protected void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }
}