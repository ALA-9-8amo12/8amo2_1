package com.example.amazighquiz.Oefen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.amazighquiz.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class MainOefenCategorieen extends AppCompatActivity {

    private static final String EXTRA_TEXT = "com.example.amazighquiz.Oefen.EXTRA_TEXT";
//    private RecyclerView recyclerView;
//    OefenCategorieAdapter adapter;
    DatabaseReference db;
    TextView Dieren1, Dieren2, Eten, Fruit, Groente, Insecten, Kleding, Kleuren, Weer;
    ImageView Dieren1img, Dieren2img, Etenimg, Fruitimg, Groenteimg, Insectenimg, Kledingimg, Kleurenimg, Weerimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_oefen_categorieen);

        db = FirebaseDatabase.getInstance().getReference().child("Testen").child("Info");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Dieren1 = findViewById(R.id.CategorieDieren1);
                Dieren2 = findViewById(R.id.CategorieDieren2);
                Eten = findViewById(R.id.CategorieEten);
                Fruit = findViewById(R.id.CategorieFruit);
                Groente = findViewById(R.id.CategorieGroente);
                Insecten = findViewById(R.id.CategorieInsecten);
                Kleding = findViewById(R.id.CategorieKleding);
                Kleuren = findViewById(R.id.CategorieKleuren);
                Weer = findViewById(R.id.CategorieWeer);

                Dieren1img = findViewById(R.id.ImageDieren1);
                Dieren2img = findViewById(R.id.ImageDieren2);
                Etenimg = findViewById(R.id.ImageEten);
                Fruitimg = findViewById(R.id.ImageFruit);
                Groenteimg = findViewById(R.id.ImageGroente);
                Insectenimg = findViewById(R.id.ImageInsecten);
                Kledingimg = findViewById(R.id.ImageKleding);
                Kleurenimg = findViewById(R.id.ImageKleuren);
                Weerimg = findViewById(R.id.ImageWeer);

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Categorie categorie = snapshot.getValue(Categorie.class);

                    switch (categorie.getCategorie()) {
                        case "Dieren1":
                            Dieren1.setText(categorie.getCategorie());
                            Glide.with(MainOefenCategorieen.this).load(categorie.getImage()).into(Dieren1img);
                            break;
                        case "Dieren2":
                            Dieren2.setText(categorie.getCategorie());
                            Glide.with(MainOefenCategorieen.this).load(categorie.getImage()).into(Dieren2img);
                            break;
                        case "Eten":
                            Eten.setText(categorie.getCategorie());
                            Glide.with(MainOefenCategorieen.this).load(categorie.getImage()).into(Etenimg);
                            break;
                        case "Fruit":
                            Fruit.setText(categorie.getCategorie());
                            Glide.with(MainOefenCategorieen.this).load(categorie.getImage()).into(Fruitimg);
                            break;
                        case "Groente":
                            Groente.setText(categorie.getCategorie());
                            Glide.with(MainOefenCategorieen.this).load(categorie.getImage()).into(Groenteimg);
                            break;
                        case "Insecten":
                            Insecten.setText(categorie.getCategorie());
                            Glide.with(MainOefenCategorieen.this).load(categorie.getImage()).into(Insectenimg);
                            break;
                        case "Kleding":
                            Kleding.setText(categorie.getCategorie());
                            Glide.with(MainOefenCategorieen.this).load(categorie.getImage()).into(Kledingimg);
                            break;
                        case "Kleuren":
                            Kleuren.setText(categorie.getCategorie());
                            Glide.with(MainOefenCategorieen.this).load(categorie.getImage()).into(Kleurenimg);
                            break;
                        case "Weer":
                            Weer.setText(categorie.getCategorie());
                            Glide.with(MainOefenCategorieen.this).load(categorie.getImage()).into(Weerimg);
                            break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        // database
//        db = FirebaseDatabase.getInstance().getReference();
//
//        // define recycler in view
//        recyclerView = findViewById(R.id.recycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        FirebaseRecyclerOptions<Categorie> options
//                = new FirebaseRecyclerOptions.Builder<Categorie>()
//                .setQuery(db.child("Testen").child("Info"), Categorie.class)
//                .build();
//
//        adapter = new OefenCategorieAdapter(options);
//        recyclerView.setAdapter(adapter);
    }

//    @Override protected void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override protected void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }

    public void configureDieren1Activity(View view) {
        Intent intent = new Intent(MainOefenCategorieen.this, MainOefen.class);
        intent.putExtra(EXTRA_TEXT, "Dieren1");
        startActivity(intent);
    }

    public void configureDieren2Activity(View view) {
        Intent intent = new Intent(MainOefenCategorieen.this, MainOefen.class);
        intent.putExtra(EXTRA_TEXT, "Dieren2");
        startActivity(intent);
    }

    public void configureEtenActivity(View view) {
        Intent intent = new Intent(MainOefenCategorieen.this, MainOefen.class);
        intent.putExtra(EXTRA_TEXT, "Eten");
        startActivity(intent);
    }

    public void configureFruitActivity(View view) {
        Intent intent = new Intent(MainOefenCategorieen.this, MainOefen.class);
        intent.putExtra(EXTRA_TEXT, "Fruit");
        startActivity(intent);
    }

    public void configureGroenteActivity(View view) {
        Intent intent = new Intent(MainOefenCategorieen.this, MainOefen.class);
        intent.putExtra(EXTRA_TEXT, "Groente");
        startActivity(intent);
    }

    public void configureInsectenActivity(View view) {
        Intent intent = new Intent(MainOefenCategorieen.this, MainOefen.class);
        intent.putExtra(EXTRA_TEXT, "Insecten");
        startActivity(intent);
    }

    public void configureKledingActivity(View view) {
        Intent intent = new Intent(MainOefenCategorieen.this, MainOefen.class);
        intent.putExtra(EXTRA_TEXT, "Kleding");
        startActivity(intent);
    }

    public void configureKleurenActivity(View view) {
        Intent intent = new Intent(MainOefenCategorieen.this, MainOefen.class);
        intent.putExtra(EXTRA_TEXT, "Kleuren");
        startActivity(intent);
    }

    public void configureWeerActivity(View view) {
        Intent intent = new Intent(MainOefenCategorieen.this, MainOefen.class);
        intent.putExtra(EXTRA_TEXT, "Weer");
        startActivity(intent);
    }

}