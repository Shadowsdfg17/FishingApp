package com.example.fishingapp.interfaces;

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
    }


}
