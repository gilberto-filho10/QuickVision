package com.example.quickvision;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Agendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

        getSupportActionBar().hide();
    }
}