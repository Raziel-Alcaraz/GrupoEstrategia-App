package com.razielalcaraz.grupoestrategia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import im.delight.android.webview.AdvancedWebView;

public class advancedBrowserActivity extends AppCompatActivity  implements AdvancedWebView.Listener  {
    private AdvancedWebView mWebView;
    String TAG="aba";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_browser);
        ImageView imageView = (ImageView) findViewById(R.id.ImageLoad2);

        Glide.with(this).load(R.drawable.loading).into(imageView);
        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        mWebView.setListener((Activity) this, (AdvancedWebView.Listener) this);
        mWebView.setMixedContentAllowed(false);
        mWebView.loadUrl(MainActivity.webTarget);

    }
    public void splashLoading(){

        ImageView loadingImage = (ImageView) findViewById(R.id.ImageLoad2);
        loadingImage.setVisibility(View.VISIBLE);
        Log.d(TAG, "cargando");


    }
    public void removeLoading(){

        ImageView loadingImage = (ImageView) findViewById(R.id.ImageLoad2);
        loadingImage.setVisibility(View.GONE);
        Log.d(TAG, "cerrado");


    }
    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) { return; }
        // ...
        super.onBackPressed();
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {splashLoading(); }

    @Override
    public void onPageFinished(String url) {removeLoading(); }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) { }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) { }

    @Override
    public void onExternalPageRequest(String url) { }
}