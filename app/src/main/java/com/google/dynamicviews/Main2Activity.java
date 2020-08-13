package com.google.dynamicviews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText edtName, edtAge;
    Button btnSaveData;
    SharedPreferences.Editor editor;
    String MY_PREFS_NAME = "MyApp";
    String name;
    int age;
    TextView tvName, tvAge;
    String n;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        btnSaveData = findViewById(R.id.btnSaveData);
        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        getValues();


        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edtName.getText().toString().trim();
                age = Integer.parseInt(edtAge.getText().toString());

                if (name.isEmpty()) {
                    Toast.makeText(Main2Activity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                } else if (age == 0) {
                    Toast.makeText(Main2Activity.this, "Enter Age", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putString("name", name);
                    editor.putInt("age", age);
                    editor.apply();
                    getValues();
                    Toast.makeText(Main2Activity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getValues() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        n = prefs.getString("name", "No Value");
        a = prefs.getInt("age", 0);

        tvName.setText("Name: " + n);
        tvAge.setText("Age: " + a);
    }
}