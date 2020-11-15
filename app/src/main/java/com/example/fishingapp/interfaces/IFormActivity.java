package com.example.fishingapp.interfaces;

public interface IFormActivity {

        public interface view{
            void finishFormActivity();
        }

        public interface Presenter{
            void onClickSaveFish();
        }
    }

