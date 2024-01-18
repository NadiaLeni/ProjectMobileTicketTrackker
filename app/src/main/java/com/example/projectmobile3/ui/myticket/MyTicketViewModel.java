package com.example.projectmobile3.ui.myticket;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyTicketViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyTicketViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is my ticket fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}