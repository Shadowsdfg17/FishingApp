package com.example.fishingapp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.fishingapp.interfaces.ISearchActivity;
import com.example.fishingapp.models.FishModel;

import java.util.ArrayList;

public class searchPresenter implements ISearchActivity.Presenter {
    private ISearchActivity.View view;
    private static final String TAG = "presenters/searchPresenter";
    private FishModel model = new FishModel();

    public searchPresenter(ISearchActivity.View view) {this.view = view;}

    @SuppressLint("LongLogTag")
    @Override
    public void OnClickSearchButton() {
        Log.d(TAG, "Inside onClickSearchButton");
        view.finishSearchActivity();

    }

    @SuppressLint("LongLogTag")
    @Override
    public void OnClickDate() {
        Log.d(TAG, "Inside onClickDate");
        view.showDate();
    }

    public ArrayList<String> getAllSex() {
        return model.getAllSexs();
    }

    @Override
    public void onClickHelp() {
        view.startHelpActivity();
    }
}
