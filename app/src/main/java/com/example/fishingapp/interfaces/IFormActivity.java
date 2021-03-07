package com.example.fishingapp.interfaces;

import com.example.fishingapp.models.EntityFish;

import java.util.ArrayList;

public interface IFormActivity {

        public interface view{
            void finishFormActivity(EntityFish fish);

            void finishFormActivity();

            void showDate();

            void alertDeleteImage();

            void selectImage();

            void showError();

            void IntentChooser();

            void startHelpActivity();


        }

        public interface Presenter{

            void onClickSaveFish(EntityFish fish, boolean fl);

            void OnClickDate();

            String getError(String error_code);

            void onClickDeleteForm();

            void onClickAcceptDelete();

            void onClickImage();

            void PermissionGranted();

            void PermissionDenied();

            boolean insertFish(EntityFish fish);

            ArrayList<String> getAllSex();

            EntityFish getItemsById(String id);

            void onClickHelp();

        }
    }

