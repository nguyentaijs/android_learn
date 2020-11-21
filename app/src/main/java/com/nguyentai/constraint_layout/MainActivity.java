package com.nguyentai.constraint_layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = (Button) findViewById(R.id.btnReg);
        EditText edtUserName = (EditText) findViewById(R.id.txtUserName);
        EditText edtEmail = (EditText) findViewById(R.id.txtEmail);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUserName.getText().toString();
                String email = edtEmail.getText().toString();
                if (userName.isEmpty() || email.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You have to input all the fields", Toast.LENGTH_LONG).show();
                } else {
                    String toastMessage = "Register succesfully!\nWelcome " + userName + "(" + email + ")";
                    Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}