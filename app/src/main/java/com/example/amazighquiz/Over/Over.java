package com.example.amazighquiz.Over;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.amazighquiz.MainActivity;
import com.example.amazighquiz.R;
import com.google.firebase.auth.FirebaseAuth;

public class Over extends AppCompatActivity {

    private Button buttonLogin, buttonRegister, buttonLogout, buttonHome;

    private TextView textViewAccount;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over);

        mAuth = FirebaseAuth.getInstance();

        buttonLogin = (Button) findViewById(R.id.signIn);
        buttonRegister = (Button) findViewById(R.id.registreer);
        buttonLogout = (Button) findViewById(R.id.logout);
        buttonHome = (Button) findViewById(R.id.home);

        textViewAccount = (TextView) findViewById(R.id.textViewAccount);

        if (mAuth.getCurrentUser() != null) {
            buttonLogin.setVisibility(View.GONE);
            buttonRegister.setVisibility(View.GONE);
            buttonLogout.setVisibility(View.VISIBLE);
            textViewAccount.setText("U kunt hier uw account uitloggen:");
        } else {
            buttonLogin.setVisibility(View.VISIBLE);
            buttonRegister.setVisibility(View.VISIBLE);
            buttonLogout.setVisibility(View.GONE);
            textViewAccount.setText("Login of maak een account:");
        }

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Over.this, MainActivity.class));
            }
        });
    }

    public void configureLoginActivity(View view) {
        startActivity(new Intent(Over.this, LoginActivity.class));
    }

    public void configureRegistreerActivity(View view) {
        startActivity(new Intent(Over.this, RegisterUser.class));
    }

    public void configureHomeActivity(View view) {
        startActivity(new Intent(Over.this, MainActivity.class));
    }
}