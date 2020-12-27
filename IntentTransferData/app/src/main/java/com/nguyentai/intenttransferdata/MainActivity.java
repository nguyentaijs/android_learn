package com.nguyentai.intenttransferdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

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

                // Transfer string value
                toSecondIntent.putExtra("StringValue", "This will be display as string");

                // Transfer Array
                List<String> heroes = new ArrayList<>();
                heroes.add("Random");
                heroes.add("Anti-mage");
                heroes.add("Axe");
                heroes.add("what else");
                heroes.add("Non-Lol champ");
                toSecondIntent.putStringArrayListExtra("ArrayValue", (ArrayList<String>) heroes);

                // Transfer Object
                Student stu = new Student("Tai", 20);
                toSecondIntent.putExtra("Student", stu);

                startActivity(toSecondIntent);
            }
        });
    }
}