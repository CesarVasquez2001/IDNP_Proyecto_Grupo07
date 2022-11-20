package com.example.idnpproyectogrupo07.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OverviewViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public OverviewViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is overview fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}