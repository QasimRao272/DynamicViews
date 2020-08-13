package com.google.dynamicviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ShowData extends AppCompatActivity {
    Button btnSHowImage;
    RecyclerView objectRecyclerview;
    DatabaseHandler objectDatabaseHandler;
    RVAdapter objectRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        btnSHowImage = findViewById(R.id.show_image_button);
        objectRecyclerview = findViewById(R.id.imageRV);
        objectDatabaseHandler = new DatabaseHandler(this);

        btnSHowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    public void getData() {
        try {
            objectRvAdapter = new RVAdapter(objectDatabaseHandler.getAllImagesData());
            objectRecyclerview.setHasFixedSize(true);
            objectRecyclerview.setLayoutManager(new LinearLayoutManager(this));
            objectRecyclerview.setAdapter(objectRvAdapter);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}