package com.project.csci3130.dalrs;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * The type Sixth fragment.
 */
public class SixthFragment extends Fragment {
    /**
     * The My view.
     */
    View myView;
    WebView webView;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Important Date");
        myView = inflater.inflate(R.layout.sixth_layout,container,false);
        webView = myView.findViewById(R.id.webview);
        webView.loadUrl("https://www.dal.ca/academics/important_dates.html");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("foo://"))
                    return true;
                return false;
            }
        });
        return myView;
    }
}
