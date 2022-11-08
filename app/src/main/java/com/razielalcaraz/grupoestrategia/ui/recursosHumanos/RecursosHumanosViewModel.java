package com.razielalcaraz.grupoestrategia.ui.recursosHumanos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecursosHumanosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RecursosHumanosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Recursos humanos");
    }

    public LiveData<String> getText() {
        return mText;
    }
}