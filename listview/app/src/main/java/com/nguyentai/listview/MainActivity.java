package com.nguyentai.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = new ArrayList<>();
        names.add("Tuan");
        names.add("Thuy");
        names.add("Tu");
        names.add("Tai");

        ArrayAdapter<String> adapterNames = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, names);

        ListView lstName = (ListView) findViewById(R.id.lstNames);
        lstName.setAdapter(adapterNames);
    }
}