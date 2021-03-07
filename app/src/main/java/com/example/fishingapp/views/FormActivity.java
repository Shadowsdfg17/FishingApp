package com.example.fishingapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.fishingapp.R;
import com.example.fishingapp.interfaces.IFormActivity;
import com.example.fishingapp.models.EntityFish;
import com.example.fishingapp.presenters.FormPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class FormActivity extends AppCompatActivity implements IFormActivity.view {


    //-----------VARIABLES-----------

    private String id;
    public Switch loose;

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


    private static final int REQUEST_CAPTURE_IMAGE = 200;
    private static final int REQUEST_SELECT_IMAGE = 201;
    private Uri uri;
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 123;
    private Context myContext;
    private ConstraintLayout constraintLayoutFormActivity;
    private ArrayAdapter<String> adapter;
    private EntityFish fish2 = new EntityFish();
    private String image;

    private EntityFish eFish = new EntityFish();
    private boolean update = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        presenter = new FormPresenter(this);
        ImageButton button = findViewById(R.id.buttonSave);
        imageSex = findViewById(R.id.addSex);
        loose=findViewById(R.id.loose);
        ImageButton addImage= findViewById(R.id.addImage);
        imageCalendar2 = findViewById(R.id.buttonCalendar);
        Toolbar toolbar = findViewById(R.id.toolbarSearch);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.formTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageCalendar2.setOnClickListener(v -> {
            presenter.OnClickDate();
        });


        //-----------SPINNER--------------

        Spinner spinner = (Spinner) findViewById(R.id.spinnerSex);
        ArrayList<String> sex = presenter.getAllSex();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sex);
        spinner.setAdapter(adapter);


        //----------AÑADIR SEXO------------

        AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);
        builder.setTitle(R.string.addSex);
        builder.setMessage(R.string.insertSex);
        EditText editText = new EditText(FormActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        editText.setLayoutParams(lp);
        builder.setPositiveButton(R.string.insert, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.add(editText.getText().toString());
                spinner.setSelection(adapter.getPosition(editText.getText().toString()));
                Toast.makeText(getApplicationContext(), R.string.addCorrect, Toast.LENGTH_LONG).show();
                Log.i(TAG, "Yes button Clicked");
            }
        });
        builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "Cancel button Clicked");
                dialog.dismiss();
            }
        });

        //--------DIALOGO DE ALERTA----------

        AlertDialog alertDialog = builder.create();
        alertDialog.setView(editText);
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
                    } else if (fish.setDate(DateTE.getText().toString()) == 2) {
                        DateTIL.setError(presenter.getError("Date2"));
                        DateTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));

                    }
                } else {
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
                    } else if (fish.setFish(FishTE.getText().toString()) == 2) {
                        FishTIL.setError(presenter.getError("Fish2"));
                        FishTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));

                    }
                } else {
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
                    } else if (fish.setWeight(WeightTE.getText().toString()) == 2) {
                        WeightTIL.setError(presenter.getError("Weight2"));
                        WeightTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    }
                } else {
                    Log.d("FormActivity", "Input EditText");
                    WeightTIL.setError(null);

                }
            }
        });


        //------NUMERO DE CAPTURAS---------

        TextInputLayout CapturesTIL;
        TextInputEditText CapturesTE;
        CapturesTE = findViewById(R.id.capturesTE);
        CapturesTIL = findViewById(R.id.capturesTIL);

        CapturesTE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (fish.setCaptures(CapturesTE.getText().toString()) == 1) {
                        CapturesTIL.setError(presenter.getError("Captures"));
                        CapturesTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    } else if (fish.setCaptures(CapturesTE.getText().toString()) == 2) {
                        CapturesTIL.setError(presenter.getError("Captures2"));
                        CapturesTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    }
                } else {
                    Log.d("FormActivity", "Input EditText");
                    CapturesTIL.setError(null);

                }
            }
        });

        //----------PESCADOR-------------

        TextInputLayout FisherTIL;
        TextInputEditText FisherTE;
        FisherTE = findViewById(R.id.fisherTE);
        FisherTIL = findViewById(R.id.fisherTIL);

        FisherTE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (fish.setFisher(FisherTE.getText().toString()) == 1) {
                        FisherTIL.setError(presenter.getError("Fisher"));
                        FisherTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    } else if (fish.setFisher(FisherTE.getText().toString()) == 2) {
                        FisherTIL.setError(presenter.getError("Fisher2"));
                        FisherTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    }
                } else {
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
                    } else if (fish.setInformation(InformationTE.getText().toString()) == 2) {
                        InformationTIL.setError(presenter.getError("Information2"));
                        InformationTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    }
                } else {
                    Log.d("FormActivity", "Input EditText");
                    InformationTIL.setError(null);

                }
            }
        });

        //-----------COGE LA ID-----------

        id = getIntent().getStringExtra("id");
        Log.d(TAG, "getString");
        if (id != null) {
            FishTE.setText(id);
        } else {
            //Deshbilitar el botón elimina
        }


        ImageButton buttonGallery = (ImageButton) findViewById(R.id.addImage);
        buttonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickImage();
            }
        });

        deleteImage();



        id=getIntent().getStringExtra("id");

        if(id!=null){
            EntityFish fish3=presenter.getItemsById(id);
            fish2.setId(id);

            DateTE.setText(fish3.getDate());
            FishTE.setText(fish3.getFish());
            WeightTE.setText(fish3.getWeight());
            CapturesTE.setText(fish3.getCaptures());
            FisherTE.setText(fish3.getFisher());
            InformationTE.setText(fish3.getInformation());
            image=fish3.getImage();
            if(image != null){
                byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                buttonGallery.setImageBitmap(decodedByte);
            }
            spinner.setSelection(adapter.getPosition(fish3.getSex()));

            //Recupero la info de esa entidad
        }else{
            ImageButton delete2 = (ImageButton) findViewById(R.id.buttonDelete);
            delete2.setEnabled(false);
        }


        //------------INSERTAR FORMULARIO---------

        button.setOnClickListener(v -> {
            if(eFish.setDate(DateTE.getText().toString()) == 0 &&
               eFish.setFish(FishTE.getText().toString()) == 0 &&
                eFish.setWeight(WeightTE.getText().toString()) == 0 &&
                eFish.setCaptures(CapturesTE.getText().toString()) == 0 &&
                eFish.setFisher(FisherTE.getText().toString()) == 0 &&
                eFish.setInformation(InformationTE.getText().toString()) == 0){

                eFish.setLoose(loose.isActivated());

                if (addImage != null && addImage.getDrawable() != null) {
                    Bitmap bitmap = ((BitmapDrawable) addImage.getDrawable()).getBitmap();
                    if (bitmap != null) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        String photoBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                        fish.setImage(photoBase64);
                    }
            }
                eFish.setSex(spinner.getSelectedItem().toString());
                if (id != null) {
                    update = false;
                }
                System.out.println("El pescao" + update);
                presenter.onClickSaveFish(eFish, update);

            } else {
                Log.d(TAG, "Error missing fields");
            }
            finish();
        });

    }







    //--------BORRAR IMAGEN----------

    public void deleteImage(){
        ImageButton deleteimage = (ImageButton) findViewById(R.id.buttonDeleteImage);
        deleteimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton buttonGallery = findViewById(R.id.addImage);
                buttonGallery.setImageBitmap(null);
            }
        });
    }



    //--------SE LE PIDE AL SISTEMA UNA IMAGEN DEL DISPOSITIVO----------


    public void selectPicture(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, getString(R.string.selectImage)),
                REQUEST_SELECT_IMAGE);
    }

    //--------MUESTRA FECHA-------------

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


    //--------ALERT AL BORRAR LA IMAGEN-----------

    @Override
    public void alertDeleteImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);
        builder.setTitle(R.string.deleteAlert);

        builder.setPositiveButton(R.string.confirmDelete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.onClickAcceptDelete();
                // Toast.makeText(getApplicationContext(),"Yes button Clicked", Toast.LENGTH_LONG).show();
                Log.i("Code2care ", "Yes button Clicked!");
            }
        });

        builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //    Toast.makeText(getApplicationContext(),"Cancel button Clicked",Toast.LENGTH_LONG).show();
                Log.i("Code2care ","Cancel button Clicked!");
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    //-----------SELECCIONA IMAGEN DEL DISPOSITIVO------------

    @Override
    public void selectImage() {
        // Se le pide al sistema una imagen del dispositivo
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, getResources().getString(R.string.chooseImage)),
                REQUEST_SELECT_IMAGE);
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

    //---------MUESTRA ERROR----------

    @Override
    public void showError() {
        Snackbar.make(findViewById(android.R.id.content), getResources().getString(R.string.permissionDenied), Snackbar.LENGTH_LONG).show();
    }

    //--------PIDE LOS PERMISOS-------

    @Override
    public void IntentChooser() {
        ActivityCompat.requestPermissions(FormActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
    }

    @Override
    public void startHelpActivity() {
        Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
        intent.putExtra("Ayuda", "https://shadowsdfg17.github.io/FishingApp/formulario.html");
        startActivity(intent);
    }

    //----------RESULTADO DEL PEDIDO DE PERMISOS---------

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CODE_WRITE_EXTERNAL_STORAGE_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.PermissionGranted();
                } else {
                    presenter.PermissionDenied();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //-----------RESULTADO DE LOS PERMISOS--------

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case (REQUEST_CAPTURE_IMAGE):
                if (resultCode == Activity.RESULT_OK) {
                    // Se carga la imagen desde un objeto URI al imageView
                    ImageButton imageButton = findViewById(R.id.addImage);
                    imageButton.setImageURI(uri);

                    // Se le envía un broadcast a la Galería para que se actualice
                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    mediaScanIntent.setData(uri);
                    sendBroadcast(mediaScanIntent);

                } else if (resultCode == Activity.RESULT_CANCELED) {
                    // Se borra el archivo temporal
                    File file = new File(uri.getPath());
                    file.delete();
                }
                break;

            case (REQUEST_SELECT_IMAGE):
                if (resultCode == Activity.RESULT_OK) {
                    // Se carga la imagen desde un objeto Bitmap
                    Uri selectedImage = data.getData();
                    String selectedPath = selectedImage.getPath();

                    if (selectedPath != null) {
                        // Se leen los bytes de la imagen
                        InputStream imageStream = null;
                        try {
                            imageStream = getContentResolver().openInputStream(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        // Se transformam los bytes de la imagen a un Bitmap
                        Bitmap bmp = BitmapFactory.decodeStream(imageStream);

                        // Se carga el Bitmap en el ImageButton
                        Bitmap imageScaled = Bitmap.createScaledBitmap(bmp, 200, 200, false);
                        ImageButton imageButton = findViewById(R.id.addImage);
                        imageButton.setImageBitmap(imageScaled);
                    }
                }
                break;
        }
    }


    @Override
    public void finishFormActivity() {
        Log.d(TAG, "Inside startFormActivity");
        finish();
    }
    @Override
    public void finishFormActivity(EntityFish fish) {
        Log.d(TAG, "Inside startFormActivity");
        presenter.insertFish(fish);
        finish();
    }
}