package com.google.dynamicviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowData extends AppCompatActivity {
    RecyclerView objectRecyclerview;
    DatabaseHandler objectDatabaseHandler;
    RVAdapter objectRvAdapter;
    TextView noviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        objectRecyclerview = findViewById(R.id.imageRV);
        noviews = findViewById(R.id.noviews);
        objectDatabaseHandler = new DatabaseHandler(this);
        showEmptyviews();
    }

    private void showEmptyviews() {
        if (objectDatabaseHandler.getAllImagesData().isEmpty()) {
            noviews.setVisibility(View.VISIBLE);
        } else {
            objectDatabaseHandler.getAllImagesData();
            noviews.setVisibility(View.GONE);
        }
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

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }
}