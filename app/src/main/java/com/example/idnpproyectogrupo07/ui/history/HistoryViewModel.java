package com.example.idnpproyectogrupo07.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;


public class HistoryViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    private final String pattern = "E, dd MMMM yyyy";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    private String date = simpleDateFormat.format(new Date());

    public HistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(date);
    }

    public LiveData<String> getText() {
        return mText;
    }
}