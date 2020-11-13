package com.example.fishingapp.presenters;

import com.example.fishingapp.interfaces.IListInterface;

public class ListPresenter implements IListInterface.Presenter {

    private IListInterface.view view;

    public ListPresenter(IListInterface.view view){
        this.view = view;
    }

    @Override
    public void onClickAddFish() {
        //log.d("");
        view.starFormActivity();
    }
}
