package com.nguyentai.firebasestorage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    FirebaseDatabase firebaseDatabase;
    DatabaseReference fDb;

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
        firebaseDatabase = FirebaseDatabase.getInstance();
        fDb = firebaseDatabase.getReference();

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

                final String[] fileName = new String[1];

                uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        // Continue with the task to get the download URL
                        return imgRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Upload file completed", Toast.LENGTH_SHORT).show();
                            Uri downloadUri = task.getResult();
                            if (downloadUri == null)
                                return;
                            else {
                                fDb.child("uploadFile").push().setValue(downloadUri.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(MainActivity.this, "Save to DB successfully", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(MainActivity.this, "Save to DB failure", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                return;
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Upload file failed", Toast.LENGTH_SHORT).show();
                        }
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