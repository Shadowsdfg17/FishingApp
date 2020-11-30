package com.example.fishingapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.fishingapp.R;
import com.example.fishingapp.interfaces.IListInterface;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class searchActivity extends AppCompatActivity implements IListInterface{

    private IListInterface.Presenter presenter;
    private static final String TAG = "views/searchActivity";

    private static final String CERO = "0";
    private static final String BARRA = "/";


    public final Calendar c = Calendar.getInstance();


    final int mouth = c.get(Calendar.MONTH);
    final int day = c.get(Calendar.DAY_OF_MONTH);
    final int any = c.get(Calendar.YEAR);

    EditText textDate;
    ImageButton imageCalendar2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        textDate = (EditText) findViewById(R.id.textDate);
        imageCalendar2 = (ImageButton) findViewById(R.id.imageCalendar2);
        imageCalendar2.setOnClickListener(v -> {

        });
       // presenter = new ListPresenter(this);
        Toolbar toolbar = findViewById(R.id.toolbarSearch);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.searchTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerSex);
        String[] sex = {getString(R.string.Sexfemale),getString(R.string.SexMale)};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sex));

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageCalendar2:
                obtenerFecha();
                break;
        }
    }

    private void obtenerFecha(){
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
}