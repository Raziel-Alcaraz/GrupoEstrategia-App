package com.razielalcaraz.grupoestrategia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.razielalcaraz.grupoestrategia.ui.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Objects;

public class signupActivity extends AppCompatActivity {
static String TAG="signup Activity";
int errorCount =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void irALogin(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void sendPhone(View v){
        String campoCelular = String.valueOf(((EditText) findViewById(R.id.campoCelular)).getText());
        campoCelular = "2222005822";
        Log.d(TAG, "fono: "+ campoCelular);
        RequestQueue queue = Volley.newRequestQueue(this);
        MainActivity.token =GetToken();
        String url = "https://rechnom-test.azurewebsites.net/api/BenefitsData?phone="+campoCelular+"&"
                +"tkn="+MainActivity.token;

// Request a string response from the provided URL.
        String finalCampoCelular = campoCelular;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        response=response.replace("\"", "");
                        response=response.replace("\\", "");
                        Log.d(TAG, "Response is: " + response+ ". url is: "+url);
                        JSONObject yeison = null;
                        if (Objects.equals(response, "NotFound")) {
                            Snackbar snackbar = Snackbar
                                    .make(v, "No se reconoce el usuario", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        } else {
                            try {
                                yeison = new JSONObject(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                if (yeison.getString("IdEmpleado").length() > 1) {
                                    MainActivity.idEmpleado = yeison.getString("IdEmpleado");
                                    MainActivity.pinEmpleado = yeison.getString("PIN");
                                    MainActivity.phoneEmpleado = finalCampoCelular;
                                    Log.d(TAG, MainActivity.idEmpleado + "  " + MainActivity.pinEmpleado);
                                    irAPin();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"That didn't work! "+ error);
                if(errorCount <5){
                    sendPhone(v);
                    errorCount++;
                }
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    public static int GetToken()
    {int v = Calendar.MONTH + Calendar.DAY_OF_MONTH * Calendar.HOUR_OF_DAY;
        int rem = v%7;
        String retorno=String.valueOf(v)+ String.valueOf(rem);
        Log.d(TAG, retorno);
        return Integer.parseInt(retorno);
    }
    public void irAPin(){
        Intent intent = new Intent(this, ingresarPin.class);
        startActivity(intent);
    }
}