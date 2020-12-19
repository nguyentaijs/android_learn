package com.nguyentai.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

        lstName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, names.get(position), Toast.LENGTH_LONG).show();
            }
        });

        lstName.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, names.get(position), Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}