package com.example.fishingapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.fishingapp.R;
import com.example.fishingapp.interfaces.IFormActivity;
import com.example.fishingapp.presenters.FormPresenter;

public class FormActivity extends AppCompatActivity implements IFormActivity.view {

    private IFormActivity.Presenter presenter;
    private static final String TAG = "views/FormActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        presenter = new FormPresenter(this);
        ImageButton button = findViewById(R.id.buttonSave);
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.formTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button.setOnClickListener(v -> {
            presenter.onClickSaveFish();

        });


    }

    @Override
    public void finishFormActivity() {
        Log.d(TAG, "Inside startFormActivity");
        finish();
    }
}