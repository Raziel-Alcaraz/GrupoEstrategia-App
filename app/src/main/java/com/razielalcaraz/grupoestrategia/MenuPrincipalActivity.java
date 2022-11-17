package com.razielalcaraz.grupoestrategia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }
    public void placeholderActivity(View v){
        int id =v.getId();
        MainActivity.webTarget=checkId(id);
        Intent intent = new Intent(this, placeholderActivity.class);
        startActivity(intent);
    }
    public void irAMainActivity(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void irAWBActivity(View v){
        int id =v.getId();
        MainActivity.webTarget=checkId(id);
        Intent intent = new Intent(this, advancedBrowserActivity.class);
        startActivity(intent);
    }
    public String checkId(int id){
       if(findViewById(R.id.descuentos)==findViewById(id)){
           return "https://www.tdu.com.mx/home";
       }else if(findViewById(R.id.vida)==findViewById(id)){
           return "https://www.vrim.com.mx/";
       }else if(findViewById(R.id.rrhh)==findViewById(id)){
           return "https://grupoestrategia.mx/";
       }else if(findViewById(R.id.academico)==findViewById(id)){
           return "https://www.udemy.com/";
       }else if(findViewById(R.id.avisos)==findViewById(id)){
           return "https://grupoestrategia.mx/";
       }else if(findViewById(R.id.entretenimiento)==findViewById(id)){
           return "https://cinepolis.com/";
       }else{
           return "https://www.topstyleshop.com/";
       }


    }
}