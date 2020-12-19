package com.nguyentai.firebaseauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText edtEmail;
    EditText edtPassword;
    Button btnRegister;
    private FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        edtEmail = findViewById(R.id.editTextEmail);
        edtPassword = findViewById(R.id.editTextPassword);
        btnRegister = findViewById(R.id.buttonRegister);

        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                registerAccountWithEmailAndPassword(email, password);
            }
        });
    }


    private void registerAccountWithEmailAndPassword(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Register completed with email: " + email, Toast.LENGTH_SHORT).show();
                } else {
                    Log.w(TAG, task.getException());
                    Toast.makeText(MainActivity.this, "Registration failed, message: " + task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}