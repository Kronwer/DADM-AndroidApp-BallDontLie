package com.example.dadmballdontlie;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SharedViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;

    public SharedViewModelFactory(Application application) {
        mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> aClass) {
        return (T) new SharedViewModel(mApplication);
    }
}
