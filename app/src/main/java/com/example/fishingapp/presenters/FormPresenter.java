package com.example.fishingapp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.fishingapp.interfaces.IFormActivity;
import com.example.fishingapp.interfaces.IListInterface;

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
}
