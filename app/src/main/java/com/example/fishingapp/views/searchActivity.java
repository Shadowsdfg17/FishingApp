package com.example.fishingapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.fishingapp.R;
import com.example.fishingapp.interfaces.IListInterface;
import com.example.fishingapp.interfaces.ISearchActivity;
import com.example.fishingapp.presenters.searchPresenter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class searchActivity extends AppCompatActivity implements ISearchActivity.View {

    private ISearchActivity.Presenter presenter;
    private static final String TAG = "views/searchActivity";

    private static final String CERO = "0";
    private static final String BARRA = "/";

    private ImageButton searchButton;


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
        textDate = (EditText) findViewById(R.id.editTextDate);
        imageCalendar2 = (ImageButton) findViewById(R.id.imageCalendar2);
        searchButton= findViewById(R.id.buttonSearch);
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

        Spinner spinner = (Spinner) findViewById(R.id.spinnerSex);
        String[] sex = {getString(R.string.Sexfemale),getString(R.string.SexMale)};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sex));

    }





    @Override
    public void finishSearchActivity() {
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
}