package com.nguyentai.firebasedb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.UserHandle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyentai.firebasedb.entities.User;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbRef = FirebaseDatabase.getInstance().getReference();

        // Create single key value pair
        dbRef.child("stringValue").setValue("Hello world");

        // Create class
        User sampleUser = new User("taint", "Nguyen Thanh Tai", "random");
        dbRef.child("user").setValue(sampleUser);

        // Map
        Map<String, Integer> ages = new HashMap<>();
        ages.put("taint", 27);
        ages.put("tuanna", 54);
        ages.put("thuynt", 52);
        ages.put("tuna", 33);

        //dbRef.child("ages").setValue(ages);

        dbRef.child("ages").push().setValue(ages, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error == null) {
                    Toast.makeText(MainActivity.this, "New ages updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, String.format("Failure in saving DB, %1", error.getMessage()), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}