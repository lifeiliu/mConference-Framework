package org.buildmlearn.mconference.conference;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.constant.Constants;

public class Register extends Fragment implements Constants {

    private ProgressBar pbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        SharedPreferences sharedPref
                = getActivity().getApplicationContext().getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);

        WebView webview = (WebView)view.findViewById(R.id.web);
        pbar = (ProgressBar)view.findViewById(R.id.loading);
        pbar.setVisibility(View.GONE);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);

        String registerURL = sharedPref.getString(REGLINK_TAG, null);
        webview.loadUrl(registerURL);

        return view;
    }

    public class WebViewClient extends android.webkit.WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            pbar.setVisibility(View.VISIBLE);
            pbar.setProgress(0);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pbar.setVisibility(View.GONE);
            pbar.setProgress(100);
        }

    }

}
