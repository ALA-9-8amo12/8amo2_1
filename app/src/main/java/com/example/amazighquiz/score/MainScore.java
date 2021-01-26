package com.example.amazighquiz.score;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.amazighquiz.R;
import com.example.amazighquiz.Speel.Speel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainScore extends AppCompatActivity {
    String categorieString;
    String scoreString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

         DatabaseReference db = FirebaseDatabase.getInstance().getReference();


        Query query = db.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("score");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                        categorieString = snapshot.getKey();
                        scoreString = snapshot.getValue(String.class);
                        showScore();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void showScore() {
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.layout);
        TextView categorie = new TextView(this);
        TextView score = new TextView(this);
        categorie.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        score.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        categorie.setText(categorieString);
        score.setText(scoreString);
        layout.addView(categorie);
        layout.addView(score);
    }
}
