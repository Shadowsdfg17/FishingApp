package com.example.fishingapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SearchRecentSuggestionsProvider;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fishingapp.R;
import com.example.fishingapp.interfaces.IFormActivity;
import com.example.fishingapp.models.EntityFish;
import com.example.fishingapp.presenters.FormPresenter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class FormActivity extends AppCompatActivity implements IFormActivity.view {

    private String id;

    private IFormActivity.Presenter presenter;
    private static final String TAG = "views/FormActivity";

    private static final String CERO = "0";
    private static final String BARRA = "/";

    public final Calendar c = Calendar.getInstance();


    public final int mouth = c.get(Calendar.MONTH);
    public final int day = c.get(Calendar.DAY_OF_MONTH);
    public final int any = c.get(Calendar.YEAR);

    ImageButton imageCalendar2;
    TextInputEditText DateTE;
    ImageButton imageSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        presenter = new FormPresenter(this);
        ImageButton button = findViewById(R.id.buttonSearch);
        imageSex=findViewById(R.id.addSex);
        imageCalendar2=findViewById(R.id.buttonCalendar);
        Toolbar toolbar = findViewById(R.id.toolbarSearch);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.formTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button.setOnClickListener(v -> {
            presenter.onClickSaveFish();

        });

        imageCalendar2.setOnClickListener(v -> {
            presenter.OnClickDate();
        });

        //-----------SPINNER--------------

        Spinner spinner = (Spinner) findViewById(R.id.spinnerSex);
        String[] sex = {getString(R.string.Sexfemale),getString(R.string.SexMale)};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sex));


        //----------AÑADIR SEXO------------
        AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);
        builder.setTitle(R.string.addSex);
        builder.setMessage(R.string.insertSex);
        EditText dialog = new EditText(FormActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        dialog.setLayoutParams(lp);
        builder.setPositiveButton(R.string.insert, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), R.string.addCorrect,Toast.LENGTH_LONG).show();
                Log.i(TAG, "Yes button Clicked");
            }
        });
        builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG,"Cancel button Clicked");
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setView(dialog);
        imageSex.setOnClickListener(v -> {
            alertDialog.show();
        });




        //-----------GENERA LOS MENSAJES DE ERROR---------------

        //------FECHA-----

        EntityFish fish;
        fish = new EntityFish();

        TextInputLayout DateTIL;
        DateTE = findViewById(R.id.dateTE);
        DateTIL = findViewById(R.id.dateTIL);

        DateTE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (fish.setDate(DateTE.getText().toString()) == 1) {
                        DateTIL.setError(presenter.getError("Date"));
                        DateTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    } else if(fish.setDate(DateTE.getText().toString()) == 2) {
                        DateTIL.setError(presenter.getError("Date2"));
                        DateTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));

                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                    DateTIL.setError(null);


                }
            }
        });

    //--------TIPO DE PEZ-------------

        TextInputLayout FishTIL;
        TextInputEditText FishTE;
        FishTE = findViewById(R.id.fishTE);
        FishTIL = findViewById(R.id.fishTIL);

        FishTE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (fish.setFish(FishTE.getText().toString()) == 1) {
                        FishTIL.setError(presenter.getError("Fish"));
                        FishTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    } else if(fish.setFish(FishTE.getText().toString()) == 2){
                        FishTIL.setError(presenter.getError("Fish2"));
                        FishTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));

                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                    FishTIL.setError(null);

                }
            }
        });


        //----------PESO---------

        TextInputLayout WeightTIL;
        TextInputEditText WeightTE;
        WeightTE = findViewById(R.id.weightTE);
        WeightTIL = findViewById(R.id.weightTIL);

        WeightTE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (fish.setWeight(WeightTE.getText().toString()) == 1) {
                        WeightTIL.setError(presenter.getError("Weigth"));
                        WeightTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                        } else if(fish.setFish(WeightTE.getText().toString()) == 2) {
                        WeightTIL.setError(presenter.getError("Weight2"));
                        WeightTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                    WeightTIL.setError(null);

                }
            }
        });


        //------NUMERO DE CAPTURAS---------

        TextInputLayout CapturesTIL;
        TextInputEditText CapturesTE;
        CapturesTE= findViewById(R.id.capturesTE);
        CapturesTIL = findViewById(R.id.capturesTIL);

        CapturesTE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (fish.setCaptures(CapturesTE.getText().toString()) == 1) {
                        CapturesTIL.setError(presenter.getError("Captures"));
                        CapturesTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    } else if(fish.setFish(CapturesTE.getText().toString()) == 2) {
                        CapturesTIL.setError(presenter.getError("Captures2"));
                        CapturesTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                    CapturesTIL.setError(null);

                }
            }
        });

        //----------PESCADOR-------------

        TextInputLayout FisherTIL;
        TextInputEditText FisherTE;
        FisherTE= findViewById(R.id.fisherTE);
        FisherTIL = findViewById(R.id.fisherTIL);

        FisherTE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (fish.setFisher(FisherTE.getText().toString()) == 1) {
                        FisherTIL.setError(presenter.getError("Fisher"));
                        FisherTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    } else if(fish.setFish(FisherTE.getText().toString()) == 2) {
                        FisherTIL.setError(presenter.getError("Fisher2"));
                        FisherTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                    FisherTIL.setError(null);
                }
            }
        });

        //-------------INFORMACIÓN--------------

        TextInputLayout InformationTIL;
        TextInputEditText InformationTE;
        InformationTE = findViewById(R.id.informationTE);
        InformationTIL = findViewById(R.id.informationTIL);

        InformationTE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (fish.setInformation(InformationTE.getText().toString()) == 1) {
                        InformationTIL.setError(presenter.getError("Information"));
                        InformationTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    } else if(fish.setFish(InformationTE.getText().toString()) == 2) {
                        InformationTIL.setError(presenter.getError("Information2"));
                        InformationTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                    InformationTIL.setError(null);

                }
            }
        });

        //-----------COGE LA ID-----------

        id = getIntent().getStringExtra("id");
        Log.d(TAG,"getString");
        if(id != null){
            FishTE.setText(id);
        }else{
            //Deshbilitar el botón elimina
        }



    }

    @Override
    public void showDate() {
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final int mesActual = month + 1;
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                DateTE.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }

        },any, mouth, day);
        recogerFecha.show();
    }


    @Override
    public void finishFormActivity() {
        Log.d(TAG, "Inside startFormActivity");
        finish();
    }
}