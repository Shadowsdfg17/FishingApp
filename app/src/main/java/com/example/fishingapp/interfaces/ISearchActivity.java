package com.example.fishingapp.interfaces;

import java.util.ArrayList;

public interface ISearchActivity {
    public interface View{
        void finishSearchActivity();
        void showDate();

    }
    public interface Presenter{
        void OnClickSearchButton();
        void OnClickDate();

        ArrayList<String> getAllSex();
    }
}
