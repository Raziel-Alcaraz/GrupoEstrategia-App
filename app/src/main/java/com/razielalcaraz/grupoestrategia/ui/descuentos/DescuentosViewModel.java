package com.razielalcaraz.grupoestrategia.ui.descuentos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DescuentosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DescuentosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}