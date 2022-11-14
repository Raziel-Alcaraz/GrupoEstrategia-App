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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class makePassword extends AppCompatActivity {
String TAG = "makePasswordActivity";
    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static String keyString = "b14ca5898a4e4133bbce2ea2315a1acm";
int errorCount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_password);
    }
    public void subirDatos(View v){
String pass1 = String.valueOf(((EditText) findViewById(R.id.pass1) ).getText());
String pass2 = String.valueOf(((EditText) findViewById(R.id.pass2) ).getText());
Log.d(TAG,pass1+"-"+pass2);
if(!pass1.equals(pass2)){
    Snackbar.make((View)findViewById(R.id.pass1), "Las contraseñas deben coincidir", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
}else{
    if(pass1.length()<5){
        Snackbar.make((View)findViewById(R.id.pass1), "Ingresa al menos 5 caracteres", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }else{
    MainActivity.passEmpleado = encrypt(pass1);
        HashMap data = new HashMap();
        data.put("tkn",MainActivity.token);
        data.put("phone",MainActivity.phoneEmpleado);
        data.put("pwd",MainActivity.passEmpleado);
        String url = "https://rechnom-test.azurewebsites.net/api/BenefitsData";
        Log.d(TAG,"Pass encriptado-------->"+ MainActivity.passEmpleado);
        sendInfo(url, data);
    }

    }
}
    public static String encrypt(final String strToEncrypt) {
        try {
            setKey(keyString);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
    public static void setKey(final String myKey) {
        MessageDigest sha = null;
        try {
            byte [] keyAln = myKey.getBytes("UTF-8");
            secretKey = new SecretKeySpec(keyAln, "AES");
        } catch ( UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**/
    public void sendInfo(String url, HashMap data){
        RequestQueue requstQueue = Volley.newRequestQueue(this);
JSONObject yeisonObyect = new JSONObject(data);
Log.d(TAG, String.valueOf(yeisonObyect));
        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url,yeisonObyect,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, String.valueOf(response));
                        irAlMain();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG,"VolleyError: "+ String.valueOf(error));
                        if(errorCount<1){
                            sendInfo(url, data);
                            errorCount++;
                        }
                    }
                }
        ){
            //here I want to post data to sever
        };
        requstQueue.add(jsonobj);

    }
    void irAlMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}