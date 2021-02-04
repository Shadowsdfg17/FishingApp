package com.example.fishingapp.presenters;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.fishingapp.R;
import com.example.fishingapp.interfaces.IFormActivity;
import com.example.fishingapp.interfaces.IListInterface;
import com.example.fishingapp.models.EntityFish;
import com.example.fishingapp.models.FishModel;
import com.example.fishingapp.views.MyApplication;

import java.util.ArrayList;

public class FormPresenter implements IFormActivity.Presenter {

    private IFormActivity.view view;
    private static final String TAG = "presenters/FormPresenter";
    private final FishModel model = new FishModel();

    public FormPresenter(IFormActivity.view view) {this.view=view;}

    @SuppressLint("LongLogTag")
    @Override
    public void onClickSaveFish(EntityFish fish, boolean fl) {
        Log.d(TAG, "Inside onClickSaveFish");
        if(fl){
            model.insert(fish);
        }else{
            model.updateFish(fish);
        }
    }

    @SuppressLint("LongLogTag")
    public void OnClickDate(){
        Log.d(TAG, "Inside onClickDate");
        view.showDate();

    }

    @Override
    public String getError(String error_code) {
        String error_msg = "";
        switch (error_code) {
            case "Date2":
                error_msg = MyApplication.getContext().getResources().getString(R.string.error_date);
                break;
            case "Date":
                error_msg = MyApplication.getContext().getResources().getString(R.string.error_date2);
                break;    
            case "Fish2":
                error_msg = MyApplication.getContext().getResources().getString(R.string.fish_error);
                break;
            case "Fish":
                error_msg = MyApplication.getContext().getResources().getString(R.string.error_fish2);
                break;   
            case "Weight2":
                error_msg = MyApplication.getContext().getResources().getString(R.string.weight_error);
                break;
            case "Weight":
                error_msg = MyApplication.getContext().getResources().getString(R.string.error_weight2);
                break;    
            case "Captures2":
                error_msg = MyApplication.getContext().getResources().getString(R.string.captures_error);
                break;
            case "Captures":
                error_msg = MyApplication.getContext().getResources().getString(R.string.error_captures2);
                break;    
            case "Fisher2":
                error_msg = MyApplication.getContext().getResources().getString(R.string.fisher_error);
                break;
            case "Fisher":
                error_msg = MyApplication.getContext().getResources().getString(R.string.error_fisher2);
                break;    
            case "Information2":
                error_msg = MyApplication.getContext().getResources().getString(R.string.information_error);
                break;
            case "Information":
                error_msg = MyApplication.getContext().getResources().getString(R.string.error_information2);
                break;    
        }
        return error_msg;
    }


    @Override
    public void onClickDeleteForm() {
        view.alertDeleteImage();
    }


    @Override
    public void onClickAcceptDelete() {
        view.finishFormActivity();
    }

    @Override
    public void onClickImage() {
        int WriteExternalStoragePermission = ContextCompat.checkSelfPermission(MyApplication.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Log.d("FormPresenter", "WRITE_EXTERNAL_STORAGE Permission: " + WriteExternalStoragePermission);

        if (WriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            //------PERMISO DENEGADO------
            // A partir de Marshmallow (6.0) se pide aceptar o rechazar el permiso en tiempo de ejecución
            // En las versiones anteriores no es posible hacerlo
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                view.IntentChooser();
                // Una vez que se pide aceptar o rechazar el permiso se ejecuta el método "onRequestPermissionsResult" para manejar la respuesta
                // Si el usuario marca "No preguntar más" no se volverá a mostrar este diálogo
            } else {
                view.showError();
            }
        } else {
            //--------PERMISO ACEPTADO-------
            view.selectImage();
        }
    }


    @Override
    public void PermissionGranted() {
        view.selectImage();
    }

    @Override
    public void PermissionDenied() {
        view.showError();
    }

    @Override
    public boolean insertFish(EntityFish fish) {
        return model.insert(fish);
    }

    @Override
    public ArrayList<String> getAllSex() {
        return model.getAllSexs();
    }

    public EntityFish getItemsById(String id){
        return model.getById(id);
    }
}
