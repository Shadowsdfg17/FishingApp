package com.example.fishingapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.fishingapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        new Timer().schedule(new TimerTask() {
            public void run() {
                Intent intent = new Intent(getApplicationContext(), listActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}