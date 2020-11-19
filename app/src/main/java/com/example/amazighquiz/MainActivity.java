package com.example.amazighquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void configureOefenActivity(View view) {
        startActivity(new Intent(MainActivity.this, Oefen.class));
    }

    public void configureSpeelActivity(View view) {
        startActivity(new Intent(MainActivity.this, Oefen.class));
    }

    public void configureScoreActivity(View view) {
        startActivity(new Intent(MainActivity.this, Oefen.class));
    }

    public void configureOverActivity(View view) {
        startActivity(new Intent(MainActivity.this, Oefen.class));
    }
}