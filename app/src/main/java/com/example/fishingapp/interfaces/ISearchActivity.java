package com.example.fishingapp.interfaces;

public interface ISearchActivity {
    public interface View{
        void finishSearchActivity();
        void showDate();

    }
    public interface Presenter{
        void OnClickSearchButton();
        void OnClickDate();
    }
}
