package com.sally.fanguubao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sally.fanguubao.R;
import com.sally.fanguubao.util.Constant;

/**
 * Created by sally on 16/6/1.
 */
public class FaqActivity extends AppCompatActivity {

    private WebView mWebView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        mWebView = (WebView) findViewById(R.id.id_faq_web_view);
        url = Constant.FAQ;
        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
