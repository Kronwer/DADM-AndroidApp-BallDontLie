package com.example.dadmballdontlie.ui.about;

import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dadmballdontlie.R;

public class AboutViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AboutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(Resources.getSystem().getString(R.string.about_message));

        //Resources.getSystem().getString(R.string.about_message)
    }

    public LiveData<String> getText() {
        return mText;
    }
}
