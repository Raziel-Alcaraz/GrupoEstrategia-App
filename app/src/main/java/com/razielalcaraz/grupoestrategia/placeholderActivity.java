package com.razielalcaraz.grupoestrategia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class placeholderActivity extends AppCompatActivity  {
String TAG ="pha";
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeholder);
        ImageView imageView = (ImageView) findViewById(R.id.ImageLoad3);

        Glide.with(this).load(R.drawable.loading).into(imageView);
        WebView browser = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);

        browser.setWebViewClient(
                new SSLWebViewClient()
        );
        browser.loadUrl(MainActivity.webTarget);
        removeLoading();
    }
    public void splashLoading(){

        ImageView loadingImage = (ImageView) findViewById(R.id.ImageLoad3);
        loadingImage.setVisibility(View.VISIBLE);
        Log.d(TAG, "cerrado");


    }
    public void removeLoading(){

        ImageView loadingImage = (ImageView) findViewById(R.id.ImageLoad3);
        loadingImage.setVisibility(View.GONE);
        Log.d(TAG, "cerrado");


    }

    private class SSLWebViewClient extends WebViewClient {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // Ignore SSL certificate errors
        }
        public void onPageFinished(WebView view, String url) {
            removeLoading();
            // do your stuff here
        }

    }

}