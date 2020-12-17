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


    public void onSwipe(int position) {
        view.startSwipe(position);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onToast() {
        Log.d(TAG,"Inside onToast");
        view.startDeleteToast();
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onClickAddFish() {
        Log.d(TAG, "Inside onClickAddFish");
        view.startFormActivity();
    }


    @SuppressLint("LongLogTag")
    public void onClickSearch(){
        Log.d(TAG, "Inside onClickSearch");
        view.startSearchActivity();
    }

    @SuppressLint("LongLogTag")
    public void onClickAboutUs() {
        Log.d(TAG, "Inside onClickAboutUs");
        view.startAboutUsActivity();
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onClickRecyclerViewItem(String id) {
        Log.d(TAG, "Inside onClickRecyclerViewItem");
        view.startFormActivity(id);

    }


}
