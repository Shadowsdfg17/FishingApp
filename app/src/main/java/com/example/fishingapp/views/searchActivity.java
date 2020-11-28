package com.example.fishingapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fishingapp.R;
import com.example.fishingapp.interfaces.IFormActivity;
import com.example.fishingapp.interfaces.IListInterface;
import com.example.fishingapp.presenters.ListPresenter;

public class searchActivity extends AppCompatActivity implements IListInterface {

    private IListInterface.Presenter presenter;
    private static final String TAG = "views/searchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
       // presenter = new ListPresenter(this);

    }
}