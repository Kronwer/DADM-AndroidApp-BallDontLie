package com.example.dadmballdontlie.ui.about;

import android.app.Application;
import android.content.res.Resources;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dadmballdontlie.R;

public class AboutViewModel extends AndroidViewModel {

    private final MutableLiveData<String> mText;

    public AboutViewModel (Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue(application.getApplicationContext().getString(R.string.about_message));
    }

    public LiveData<String> getText() {
        return mText;
    }
}
