package com.nguyentai.firebasedb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyentai.firebasedb.entities.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    DatabaseReference dbRef;
    ListView lvTitle;
    List<String> lstUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbRef = FirebaseDatabase.getInstance().getReference();

        User newUser = new User("taint", "Nguyen Thanh Tai", "random");
        User newUser2 = new User("tuanna", "Nguyen Anh Tuan", "random2");

        //dbRef.child("usr").push().setValue(newUser);
        //dbRef.child("usr").push().setValue(newUser2);

        lvTitle = (ListView) findViewById(R.id.lvTitles);
        lstUser = new ArrayList<>();
        ArrayAdapter<String> adapterTitle = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lstUser);
        lvTitle.setAdapter((ListAdapter) adapterTitle);

        // Listener for data changes
        dbRef.child("usr").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
               User usr = snapshot.getValue(User.class);
               lstUser.add(usr.getUserId() + " - " + usr.getDisplayName());
               adapterTitle.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}