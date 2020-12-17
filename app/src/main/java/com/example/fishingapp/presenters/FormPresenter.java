package com.example.fishingapp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.fishingapp.R;
import com.example.fishingapp.interfaces.IFormActivity;
import com.example.fishingapp.interfaces.IListInterface;
import com.example.fishingapp.views.MyApplication;

public class FormPresenter implements IFormActivity.Presenter {

    private IFormActivity.view view;
    private static final String TAG = "presenters/FormPresenter";

    public FormPresenter(IFormActivity.view view) {this.view=view;}

    @SuppressLint("LongLogTag")
    @Override
    public void onClickSaveFish() {
        Log.d(TAG, "Inside onClickSaveFish");
        view.finishFormActivity();
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
}
