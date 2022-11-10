package com.razielalcaraz.grupoestrategia;

import androidx.appcompat.app.AppCompatActivity;

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

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
        KeyGenerator keyGenerator;

        SecretKey secretKey;
        byte[] IV = new byte[16];
        SecureRandom random;
        random = new SecureRandom();
        random.nextBytes(IV);
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            secretKey = keyGenerator.generateKey();
            String keyString = "b14ca5898a4e4133bbce2ea2315a1acm";
            try {
                byte[] encrypt = encrypt(pass1.getBytes(), secretKey, IV);
                String encryptText = new String(encrypt, StandardCharsets.UTF_8);
                Log.d(TAG,"Encrypted: "+encryptText);
                //EncryptPass(keyString);
                MainActivity.passEmpleado = encryptText;
                HashMap data = new HashMap();
                data.put("tkn",MainActivity.token);
                data.put("phone",MainActivity.phoneEmpleado);
                data.put("pwd",MainActivity.passEmpleado);
                String url = "https://rechnom-test.azurewebsites.net/api/BenefitsData";
                sendInfo(url, data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        try{
            byte[] key = CustomDecryptor.generateKey();
            String title = CustomDecryptor.decrypt( key, pass1 );
            Log.d(TAG, "encriptado--->  "+ title);
           // deleteFolder( title, position );
        } catch(Exception e) {
            Log.d(TAG, "encriptado---> false ");
            e.printStackTrace();
        }

         */
    }

    }
}

    void EncryptPass(String pass) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String keyString = "b14ca5898a4e4133bbce2ea2315a1acm";
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(256, SecureRandom.getInstance(keyString));
       //[B@19de4a6
        SecretKey key = keygen.generateKey();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] ciphertext = cipher.doFinal(pass.getBytes(StandardCharsets.UTF_8));
        byte[] iv = cipher.getIV();
Log.d(TAG,"Crypto: "+ String.valueOf(iv));


    }

    public static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] cipherText = cipher.doFinal(plaintext);
        return cipherText;
    }
    public void sendInfo(String url, HashMap data){
        RequestQueue requstQueue = Volley.newRequestQueue(this);
JSONObject yeisonObyect = new JSONObject(data);
Log.d(TAG, String.valueOf(yeisonObyect));
        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url,yeisonObyect,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, String.valueOf(response));
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
}