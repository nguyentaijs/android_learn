package com.nguyentai.intenttransferdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent mainIntent = getIntent();
        tvResult = (TextView) findViewById(R.id.textViewReceivedData);

        String result = "";

        // Receive string value from main
        result += mainIntent.getStringExtra("StringValue");
        result += "\n";

        // Receive array values
        List<String> arrayResult = mainIntent.getStringArrayListExtra("ArrayValue");
        for (String hero: arrayResult) {
            result += hero;
            result += ", ";
        }

        // Receive object
        Student stu = (Student) mainIntent.getSerializableExtra("Student");

        result += stu.toString();

        tvResult.setText(result);
    }
}