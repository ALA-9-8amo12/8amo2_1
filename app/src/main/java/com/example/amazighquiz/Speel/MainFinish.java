package com.example.amazighquiz.Speel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.amazighquiz.MainActivity;
import com.example.amazighquiz.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainFinish extends AppCompatActivity {
    private TextView scoreText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_finish);
        scoreText = findViewById(R.id.scoreText);
        Intent intent = getIntent();
        scoreText.setText(String.format("%s / %s", intent.getStringExtra("score"), intent.getStringExtra("total")));

        FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("score/" + intent.getStringExtra("categorie"))
                .setValue(intent.getStringExtra("score") + " / " + intent.getStringExtra("total"));
    }

    public void backClick(View view) {
        Intent intent = new Intent(MainFinish.this, MainActivity.class);
        startActivity(intent);
    }
}
