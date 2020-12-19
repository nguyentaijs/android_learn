package com.nguyentai.firebasedb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyentai.firebasedb.entities.User;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    DatabaseReference dbRef;

    TextView txtTitle;
    static Button btnAndroid;
    static Button btnIOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        btnAndroid = (Button) findViewById(R.id.buttonAndroid);
        btnIOS = (Button) findViewById(R.id.buttonIOS);

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

        dbRef.child("appTitle").setValue("Random title");

        // Listener for data changes
        dbRef.child("appTitle").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txtTitle.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Button Android event
        btnAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef.child("appTitle").setValue(btnAndroid.getText());
            }
        });

        // Button iOS event
        btnIOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef.child("appTitle").setValue(btnIOS.getText());
            }
        });
    }
}