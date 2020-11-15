package com.example.fishingapp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.fishingapp.interfaces.IListInterface;

public class ListPresenter implements IListInterface.Presenter {

    private IListInterface.view view;
    private static final String TAG = "presenters/ListPresenter";

    public ListPresenter(IListInterface.view view){
        this.view = view;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onClickAddFish() {
        Log.d(TAG, "Inside onClickAddFish");
        view.starFormActivity();
    }
}
