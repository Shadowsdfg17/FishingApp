package com.example.fishingapp.interfaces;

import com.example.fishingapp.models.EntityFish;

import java.util.ArrayList;

public interface IListInterface {
    public interface view{

    void startFormActivity();

    void startSearchActivity();

    void startAboutUsActivity();

    void startFormActivity(String id);

    void startSwipe(int position);

    void startDeleteToast();

    }


    public interface Presenter{

        void onClickAddFish();

        void onClickSearch();

        void onClickAboutUs();

        void onClickRecyclerViewItem(String id);

        void onSwipe(int position);

        void onToast();

        boolean insert(EntityFish fish);

        ArrayList<EntityFish> getAllItemsSummarize();

        boolean deleteFish(EntityFish fish);

        ArrayList<EntityFish> getItemsByCriterion(String sex, String sex1);

        ArrayList<EntityFish> getItemsByAllCriterion(String sex, String date, String fish);
    }


}
