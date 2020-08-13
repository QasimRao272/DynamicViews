package com.google.dynamicviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    LinearLayout layout_list;
    Button btn_add;

    List<String> teamlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        layout_list = findViewById(R.id.layout_list);
        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(this);

        teamlist.add("Team");
        teamlist.add("Pakistan");
        teamlist.add("Australia");
        teamlist.add("England");
    }

    @Override
    public void onClick(View v) {
        addView();
    }

    private void addView() {
        final View cricketerView = getLayoutInflater().inflate(R.layout.row_add_cricketer, null, false);

        EditText editText = (EditText) cricketerView.findViewById(R.id.edit_cricketer_name);

        AppCompatSpinner spinnerteams = (AppCompatSpinner) cricketerView.findViewById(R.id.spinner_team);

        ImageView imageClose = (ImageView) cricketerView.findViewById(R.id.img_remove);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, teamlist);

        spinnerteams.setAdapter(arrayAdapter);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(cricketerView);
            }
        });

        layout_list.addView(cricketerView);

    }

    private void removeView(View view) {

        layout_list.removeView(view);

    }
}
