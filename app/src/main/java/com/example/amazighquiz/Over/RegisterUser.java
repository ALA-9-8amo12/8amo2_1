package com.example.amazighquiz.Over;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amazighquiz.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;

    private Button register;
    private EditText editTextName, editTextAge, editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.name);
        editTextAge = (EditText) findViewById(R.id.age);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register:
                registerUser();
        }
    }

    private void registerUser() {
        final String name = editTextName.getText().toString().trim();
        final String age = editTextAge.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (name.isEmpty()){
            editTextName.setError("Dit veld mag niet leeg zijn");
            editTextName.requestFocus();
            return;
        }

        if (age.isEmpty()){
            editTextAge.setError("Dit veld mag niet leeg zijn");
            editTextAge.requestFocus();
            return;
        }

        if (email.isEmpty()){
            editTextEmail.setError("Dit veld mag niet leeg zijn");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Voeg een geldig email adress toe");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextPassword.setError("Dit veld mag niet leeg zijn");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Het wachtwoord mag niet kleiner zijn dan 6 karakters");
            editTextPassword.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {

                        if (task.isSuccessful()){
                            User user = new User(name, age, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterUser.this, "Het account is gemaakt", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(RegisterUser.this, "vaild to register", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(RegisterUser.this, "vaild to register", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}