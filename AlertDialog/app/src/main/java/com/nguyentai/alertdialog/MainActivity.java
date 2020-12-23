package com.nguyentai.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvHeroes;
    List<String> heroes;
    ArrayAdapter<String> lvHeroesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        // LvHeroes events
        lvHeroes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                confirm(position);
                return false;
            }
        });
    }

    private void confirm(int position) {
        AlertDialog confirmDialog = new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        heroes.remove(position);
                        lvHeroesAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void init() {

        // Init heroes array
        heroes = new ArrayList<>();
        heroes.add("Axe");
        heroes.add("Abandon");
        heroes.add("Lina");
        heroes.add("Rylai");
        heroes.add("Anti-mage");

        // View initiation
        lvHeroes = (ListView) findViewById(R.id.lvHeros);

        // Link heroes array to listview
        lvHeroesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, heroes);
        lvHeroes.setAdapter(lvHeroesAdapter);
    }

}