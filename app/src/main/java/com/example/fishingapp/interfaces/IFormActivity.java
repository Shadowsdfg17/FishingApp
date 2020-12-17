package com.example.fishingapp.interfaces;

public interface IFormActivity {

        public interface view{
            void finishFormActivity();
            void showDate();
            void alertDeleteImage();
            void selectImage();
            void showError();
            void IntentChooser();

        }

        public interface Presenter{
            void onClickSaveFish();
            void OnClickDate();
            String getError(String error_code);
            void onClickDeleteForm();
            void onClickAcceptDelete();
            void onClickImage();
            void PermissionGranted();
            void PermissionDenied();

        }
    }

