package com.example.fishingapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.fishingapp.R;

public class aboutCrud extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_crud);
        Toolbar toolbar = findViewById(R.id.crudToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.aboutUs);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}