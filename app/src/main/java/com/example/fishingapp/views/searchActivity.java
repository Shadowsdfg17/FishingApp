package com.example.fishingapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.fishingapp.R;
import com.example.fishingapp.interfaces.ISearchActivity;
import com.example.fishingapp.presenters.searchPresenter;

import java.util.ArrayList;
import java.util.Calendar;

public class searchActivity extends AppCompatActivity implements ISearchActivity.View {

    private ISearchActivity.Presenter presenter;
    private static final String TAG = "views/searchActivity";

    private static final String CERO = "0";
    private static final String BARRA = "/";

    private ImageButton searchButton;

    public EditText editTextFish;
    public Spinner spinner;


    public final Calendar c = Calendar.getInstance();


    public final int mouth = c.get(Calendar.MONTH);
    public final int day = c.get(Calendar.DAY_OF_MONTH);
    public final int any = c.get(Calendar.YEAR);

    EditText textDate;
    ImageButton imageCalendar2;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        imageCalendar2 = (ImageButton) findViewById(R.id.imageCalendar2);
        searchButton= findViewById(R.id.buttonSave);
        searchButton.setOnClickListener(v -> {
            presenter.OnClickSearchButton();
        });
        imageCalendar2.setOnClickListener(v -> {
            presenter.OnClickDate();
        });
        presenter = new searchPresenter(this);
        Toolbar toolbar = findViewById(R.id.toolbarSearch);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.searchTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextFish = findViewById(R.id.editTextFish);
        textDate = (EditText) findViewById(R.id.editTextDate);
        spinner = (Spinner) findViewById(R.id.spinnerSexSearch);

        ArrayList<String> sex = presenter.getAllSex();
        sex.add(0,getString(R.string.selected));
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sex));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.help_menu, menu);
        return true;
    }


    //---------OPCIONES SELECCIONADAS (BUSCAR Y SOBRE NOSOTROS)---------

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.help:
                presenter.onClickHelp();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void finishSearchActivity() {
        Intent intent = new Intent();
        if(textDate != null && !textDate.getText().toString().isEmpty()){
            intent.putExtra("date", textDate.getText().toString());
        }
        if(editTextFish != null && !editTextFish.getText().toString().isEmpty()){
            intent.putExtra("fish", editTextFish.getText().toString());
        }
        if(spinner != null && !spinner.getSelectedItem().toString().equals(getString(R.string.selected))){
            intent.putExtra("sex", spinner.getSelectedItem().toString());
        }
        setResult(1, intent);
        finish();

    }

    @Override
    public void showDate() {
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final int mesActual = month + 1;
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                textDate.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }

        },any, mouth, day);
        recogerFecha.show();
    }

    @Override
    public void startHelpActivity() {
        Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
        intent.putExtra("Ayuda", "https://shadowsdfg17.github.io/FishingApp/buscar.html");
        startActivity(intent);
    }
}