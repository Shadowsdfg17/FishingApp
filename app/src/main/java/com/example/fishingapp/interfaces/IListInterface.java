package com.example.fishingapp.interfaces;

public interface IListInterface {
    public interface view{
    void startFormActivity();
    void startSearchActivity();
    }

    public interface Presenter{
        void onClickAddFish();
        void onClickSearch();
    }


}
