package com.example.fishingapp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.fishingapp.interfaces.IListInterface;
import com.example.fishingapp.models.EntityFish;
import com.example.fishingapp.models.FishModel;

import java.util.ArrayList;

public class ListPresenter implements IListInterface.Presenter {

    private IListInterface.view view;
    private static final String TAG = "presenters/ListPresenter";
    private final FishModel model = new FishModel();

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

    @Override
    public boolean insert(EntityFish fish) {
        return model.insert(fish);
    }

    @SuppressLint("LongLogTag")
    @Override
    public ArrayList<EntityFish> getAllItemsSummarize() {
        Log.d(TAG,"Inside getItemsSummarize");
        return model.getAllSummarize();
    }

    @Override
    public boolean deleteFish(EntityFish fish) {
        return model.deleteFish(fish);
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

    public ArrayList<EntityFish> getItemsByCriterion(String criterion, String type) {
        if (criterion.equals("sex")) {
            return FishModel.getItemsBySex(type);
        } else if (criterion.equals("fish")) {
            return FishModel.getItemsByFish(type);
        } else if (criterion.equals("date")) {
            return  FishModel.getItemsByDate(type);
        }
        return null;
    }

    public ArrayList<EntityFish> getItemsByAllCriterion(String sex, String date, String fish) {
        return FishModel.getItemsByAllCriterion(sex, date, fish);
    }

    @Override
    public void onClickHelp() {
        view.startHelpActivity();
    }


}
