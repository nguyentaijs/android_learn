package com.nguyentai.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnPopupMenu;
    Button btnContextMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPopupMenu = (Button) findViewById(R.id.buttonPopupMenu);
        btnContextMenu = (Button) findViewById(R.id.buttonContextMenu);

        registerForContextMenu(btnContextMenu);

        // Pop up menu
        btnPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu();
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
        menu.setHeaderTitle("Select color");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuRed:
                btnContextMenu.setBackgroundColor(Color.RED);
                break;
            case R.id.menuGreen:
                btnContextMenu.setBackgroundColor(Color.GREEN);
                break;
            case R.id.menuYellow:
                btnContextMenu.setBackgroundColor(Color.YELLOW);
                btnContextMenu.setTextColor(Color.BLACK);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void showMenu() {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, btnPopupMenu);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuAdd:
                        Toast.makeText(MainActivity.this, "Menu add clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuEdit:
                        Toast.makeText(MainActivity.this, "Menu edit clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuDelete:
                        Toast.makeText(MainActivity.this, "Menu delete clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
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