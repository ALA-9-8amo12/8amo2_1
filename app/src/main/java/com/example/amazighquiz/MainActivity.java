package com.example.amazighquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.amazighquiz.Oefen.MainOefen;
import com.example.amazighquiz.Oefen.MainOefenCategorieen;

public class MainActivity extends AppCompatActivity {

    private static final String EXTRA_TEXT = "com.example.amazighquiz.EXTRA_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void configureOefenActivity(View view) {
        startActivity(new Intent(MainActivity.this, MainOefenCategorieen.class));
    }

    public void configureSpeelActivity(View view) {
        startActivity(new Intent(MainActivity.this, MainOefenCategorieen.class));
    }

    public void configureScoreActivity(View view) {
        startActivity(new Intent(MainActivity.this, MainOefenCategorieen.class));
    }

    public void configureOverActivity(View view) {
        startActivity(new Intent(MainActivity.this, MainOefenCategorieen.class));
    }

}