package com.example.fishingapp.interfaces;

public interface IListInterface {
    public interface view{
    void startFormActivity();
    void startSearchActivity();
    void starAboutUsActivity();
    }

    public interface Presenter{
        void onClickAddFish();
        void onClickSearch();
        void onClickAboutUs();
    }


}
