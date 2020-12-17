package com.example.fishingapp.interfaces;

public interface IFormActivity {

        public interface view{
            void finishFormActivity();
            void showDate();
        }

        public interface Presenter{
            void onClickSaveFish();
            void OnClickDate();
            String getError(String error_code);

        }
    }

