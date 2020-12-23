package com.nguyentai.customdialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView tvLoginTrigger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        tvLoginTrigger = (TextView) findViewById(R.id.tvLogInTrigger);

        tvLoginTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginDialog();
            }
        });
    }

    private void openLoginDialog() {
        Dialog loginDialog = new Dialog(this);
        loginDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        loginDialog.setContentView(R.layout.login_dialog);
        loginDialog.setCanceledOnTouchOutside(false);
        loginDialog.show();

        // Init dialog layout
        EditText edtUserName = (EditText) loginDialog.findViewById(R.id.edtUserName);
        EditText edtPassword = (EditText) loginDialog.findViewById(R.id.edtPassword);
        Button btnOkay = (Button) loginDialog.findViewById(R.id.btnOkay);
        Button btnCancel = (Button) loginDialog.findViewById(R.id.btnCancel);

        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();

                if(userName.equalsIgnoreCase("tai") && password.equalsIgnoreCase("123")) {
                    Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "User or password is incorrect", Toast.LENGTH_SHORT).show();
                }
                tvLoginTrigger.setText("You are logged in!!!");
                loginDialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDialog.dismiss();
            }
        });
    }
}