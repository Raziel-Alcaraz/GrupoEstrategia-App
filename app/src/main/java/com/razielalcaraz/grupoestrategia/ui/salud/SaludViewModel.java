package com.razielalcaraz.grupoestrategia.ui.salud;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SaludViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SaludViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}