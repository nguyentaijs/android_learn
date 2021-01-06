package com.nguyentai.intenttransferdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

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

        // Receive bundle
        result += "\n Bundle \n";
        Bundle mainBundle = mainIntent.getBundleExtra("Bundle");
        if (mainBundle != null) {
            // String value
            result += mainBundle.getString("BundleStringValue");
            result += "\n";
            // Array values
            for (String hero : mainBundle.getStringArrayList("BundleArrayValue")) {
                result += hero + ", ";
            }
            result += "\n";
            // Object
            result += mainBundle.getSerializable("BundleStudent");
        }

        tvResult.setText(result);
    }
}