package com.razielalcaraz.grupoestrategia.ui.descuentos;

import static com.razielalcaraz.grupoestrategia.MainActivity.removeLoading;
import static com.razielalcaraz.grupoestrategia.MainActivity.splashLoading;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.razielalcaraz.grupoestrategia.R;

import im.delight.android.webview.AdvancedWebView;

public class DescuentosFragment extends Fragment implements AdvancedWebView.Listener {

    private DescuentosViewModel homeViewModel;
    private AdvancedWebView mWebView;
    static String TAG ="Home Fragment";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(DescuentosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        mWebView = (AdvancedWebView) root.findViewById(R.id.webview);
        mWebView.setListener(getActivity(),this);
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
    public void onPageStarted(String url, Bitmap favicon) {Log.w(TAG,"pagina iniciada");splashLoading(); }

    @Override
    public void onPageFinished(String url) {Log.w(TAG,"pagina cargada"); removeLoading();  }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {Log.w(TAG,"pagina iniciada"); }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {Log.w(TAG,"pagina iniciada"); }

    @Override
    public void onExternalPageRequest(String url) {Log.w(TAG,"pagina iniciada"); }
}