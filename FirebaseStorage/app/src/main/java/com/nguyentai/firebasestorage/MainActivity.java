package com.nguyentai.firebasestorage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;

    private static final String TAG = "MainActivity";

    ImageView imgView;
    Button btnUpload;
    FirebaseStorage fStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        imgView = (ImageView) findViewById(R.id.imageViewImage);
        btnUpload = (Button) findViewById(R.id.buttonUpload);
        fStorage = FirebaseStorage.getInstance("gs://fir-db-be499.appspot.com");

        // On click even btnUpload
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Upload to Firebase storage
                StorageReference sRef = fStorage.getReference();

                StorageReference imgRef = sRef.child("captureImg" + System.currentTimeMillis());

                imgView.setDrawingCacheEnabled(true);
                imgView.buildDrawingCache();

                Bitmap imgBitmap = ((BitmapDrawable) imgView.getDrawable()).getBitmap();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                imgBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] imgbytes = baos.toByteArray();

                UploadTask uploadTask = imgRef.putBytes(imgbytes);

                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error", e);
                        Toast.makeText(MainActivity.this, "Failed, msg:" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.i(TAG, taskSnapshot.getMetadata().toString());
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Onclick event imgView
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(captureIntent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Bitmap imgBitmap = (Bitmap) data.getExtras().get("data");
            imgView.setImageBitmap(imgBitmap);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}