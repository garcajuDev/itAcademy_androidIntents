package com.intents.juangarcia.intentsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Grettings extends AppCompatActivity {

    TextView nameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grettings);

        String user = getIntent().getStringExtra("userName");

        nameView = findViewById(R.id.txtName);
        nameView.setText(user);
    }
}
