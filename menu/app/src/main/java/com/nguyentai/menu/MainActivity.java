package com.nguyentai.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                Toast.makeText(this, "Menu 1 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu2:
                Toast.makeText(this, "Menu 2 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu31:
                Toast.makeText(this, "Menu 31 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu32:
                Toast.makeText(this, "Menu 32 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuAlone:
                Toast.makeText(this, "Menu Alone clicked", Toast.LENGTH_SHORT).show();
                break;

        }


        return super.onOptionsItemSelected(item);
    }
}