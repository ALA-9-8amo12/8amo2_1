package com.example.amazighquiz.Speel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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

public class MainSpeel extends AppCompatActivity implements SpeelAdapter.OnImageListener {

    private static final String TAG = "MyActivity";
    TextView vraag, amazighWoord;
    SpeelAdapter adapter;
    DatabaseReference db;
    RecyclerView recyclerView;
    String categorie;
    String antwoord;
    List<Speel> speelList;
    List<Speel> quizList;
    List<Integer> repNumb;
    int count = 0;
    int guesses = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_speel);

        vraag = findViewById(R.id.vraag);
        amazighWoord = findViewById(R.id.amazighWoord);

        Intent intent = getIntent();
        categorie = intent.getStringExtra(MainSpeelCategorieen.EXTRA_TEXT);

        db = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(MainSpeel.this, 2));
        speelList = new ArrayList<>();

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

                        speel.setAmazigh(snapshot.child("Amazigh").getValue(String.class));
                        speel.setImage(snapshot.child("Image").getValue(String.class));
                        speel.setGeluid(snapshot.child("Geluid").getValue(String.class));
                        speel.setVraag(snapshot.child("Vraag").getValue(String.class));
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
        quizList = new ArrayList<>();
        repNumb = new ArrayList();
        int rGetal;
        Boolean repBool = false;

        vraag.setText(speelList.get(count).getVraag());
        amazighWoord.setText(speelList.get(count).getAmazigh());

        quizList.add(speelList.get(count));
        antwoord = speelList.get(count).toString();
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
                quizList.add(speelList.get(rGetal));

            } else {
                i--;
            }
        }
        Collections.shuffle(quizList);

        adapter = new SpeelAdapter(getApplicationContext(), quizList, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onImageClick(int position) {
        if (antwoord.equals(quizList.get(position).toString())) {
            Toast.makeText(this, "correct", Toast.LENGTH_SHORT).show();
            guesses = 3;
            count++;
            startAdapter();

        } else if (guesses > 0){
            Toast.makeText(this, "Fout: " + guesses + " gokken over"  , Toast.LENGTH_SHORT).show();
            guesses--;
        } else {
            count++;
            guesses = 3;
            startAdapter();
        }
    }
}