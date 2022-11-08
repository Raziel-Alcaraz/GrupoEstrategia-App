package com.razielalcaraz.grupoestrategia.ui.ideas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IdeasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public IdeasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Ideas productivas");
    }

    public LiveData<String> getText() {
        return mText;
    }
}