package com.google.dynamicviews;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Main3Activity extends AppCompatActivity {
    EditText imgeDetailsET;
    Button saveBtn, moveToShowActivity;
    ImageView objectImageView;
    public static final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    private Bitmap imageToStore;
    DatabaseHandler objectDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        imgeDetailsET = findViewById(R.id.edtName);
        saveBtn = findViewById(R.id.btnSave);
        moveToShowActivity = findViewById(R.id.btnShow);
        objectImageView = findViewById(R.id.imgView);
        objectDatabaseHandler = new DatabaseHandler(this);

        objectImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objectIntent = new Intent();
                objectIntent.setType("image/*");
                objectIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(objectIntent, PICK_IMAGE_REQUEST);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!imgeDetailsET.getText().toString().isEmpty() && objectImageView.getDrawable() != null && imageToStore != null) {
                    objectDatabaseHandler.storeImage(new ModelClass(imgeDetailsET.getText().toString(), imageToStore));
                }
            }
        });

        moveToShowActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShowData.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);
                objectImageView.setImageBitmap(imageToStore);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}