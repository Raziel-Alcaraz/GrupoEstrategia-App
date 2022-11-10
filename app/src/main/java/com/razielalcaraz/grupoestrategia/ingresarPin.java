package com.razielalcaraz.grupoestrategia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.poovam.pinedittextfield.PinField;
import com.poovam.pinedittextfield.SquarePinField;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ingresarPin extends AppCompatActivity {
String TAG = "incresarPin Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_pin);
        Log.d(TAG, "onCreate");
        leerCamposPin();
    }
    public void leerCamposPin(){
        final SquarePinField squarePinField = findViewById(R.id.squareField);
        squarePinField.setOnTextCompleteListener(new PinField.OnTextCompleteListener() {
            @Override
            public boolean onTextComplete (@NotNull String enteredText) {
                Toast.makeText(ingresarPin.this,enteredText,Toast.LENGTH_SHORT).show();
                validarPin(enteredText);
                return true; // Return false to keep the keyboard open else return true to close the keyboard
            }
        });
    }
    public void botonValidar(View v){
        final SquarePinField squarePinField = findViewById(R.id.squareField);
        validarPin(String.valueOf(squarePinField.getText()));
    }
    public void validarPin(String enteredText){
        Log.d(TAG,"entered: "+enteredText+", requested: "+ MainActivity.pinEmpleado );
        if(Objects.equals(enteredText, MainActivity.pinEmpleado)){
            Intent intent = new Intent(this, makePassword.class);
            startActivity(intent);
        }else {
            Snackbar.make(findViewById(R.id.squareField), "Revisa el pin que ingresaste", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}