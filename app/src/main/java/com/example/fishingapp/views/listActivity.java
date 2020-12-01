package com.example.fishingapp.views;

import android.content.Intent;
import android.os.Bundle;

import com.example.fishingapp.R;
import com.example.fishingapp.interfaces.IListInterface;
import com.example.fishingapp.presenters.ListPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class listActivity extends AppCompatActivity implements IListInterface.view {

    private static final String TAG = "/views/ListActivity";
    private IListInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        presenter = new ListPresenter(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.listTitle);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            presenter.onClickAddFish();

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.button_search:
                presenter.onClickSearch();
                break;
            case R.id.aboutus:
                presenter.onClickAboutUs();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "Starting onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "Starting onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "Starting onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Starting onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "Starting onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Starting onDestroy");
        super.onDestroy();
    }

    @Override
    public void startFormActivity() {
        Log.d(TAG, "Inside startFormActivity");
        Intent intent = new Intent(getApplicationContext(), FormActivity.class);
        startActivity(intent);
    }


    public void startSearchActivity(){
        Log.d(TAG, "Inside startSearchActivity");
        Intent intent = new Intent(getApplicationContext(), searchActivity.class);
        startActivity(intent);
    }

    @Override
    public void startAboutUsActivity() {
        Log.d(TAG, "Inside startAboutUsActivity");
        Intent intent = new Intent(getApplicationContext(), aboutCrud.class);
        startActivity(intent);
    }


}