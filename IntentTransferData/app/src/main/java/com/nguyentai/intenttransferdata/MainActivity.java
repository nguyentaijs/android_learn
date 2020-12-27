package com.nguyentai.intenttransferdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnToSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToSecond = (Button) findViewById(R.id.buttonGoToSecond);

        btnToSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toSecondIntent = new Intent(MainActivity.this, SecondActivity.class);
                toSecondIntent.putExtra("StringValue", "This will be display as string");
                startActivity(toSecondIntent);
            }
        });
    }
}