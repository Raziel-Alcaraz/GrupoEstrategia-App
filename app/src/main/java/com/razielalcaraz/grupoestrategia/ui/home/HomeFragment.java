package com.razielalcaraz.grupoestrategia.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.razielalcaraz.grupoestrategia.R;

import im.delight.android.webview.AdvancedWebView;

public class HomeFragment extends Fragment implements AdvancedWebView.Listener {

    private HomeViewModel homeViewModel;
    private AdvancedWebView mWebView;
    static String TAG ="Home Fragment";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        mWebView = (AdvancedWebView) root.findViewById(R.id.webview);
       // mWebView.setListener(getActivity(),getActivity());
        mWebView.setMixedContentAllowed(true);
        mWebView.loadUrl("https://topstyleshop.com/");

        return root;
    }
    @SuppressLint("NewApi")
    @Override
    public void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }
    @SuppressLint("NewApi")
    @Override
    public void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {Log.w(TAG,"pagina iniciada"); }

    @Override
    public void onPageFinished(String url) {Log.w(TAG,"pagina iniciada");  }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {Log.w(TAG,"pagina iniciada"); }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {Log.w(TAG,"pagina iniciada"); }

    @Override
    public void onExternalPageRequest(String url) {Log.w(TAG,"pagina iniciada"); }
}