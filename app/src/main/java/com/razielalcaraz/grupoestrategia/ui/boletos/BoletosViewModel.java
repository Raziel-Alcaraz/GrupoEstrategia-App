package com.razielalcaraz.grupoestrategia.ui.boletos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BoletosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BoletosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Boletos de cine");
    }

    public LiveData<String> getText() {
        return mText;
    }
}