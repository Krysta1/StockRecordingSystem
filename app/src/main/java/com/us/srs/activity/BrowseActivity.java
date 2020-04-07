package com.us.srs.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


import com.us.srs.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrowseActivity extends AppCompatActivity {
    @BindView(R.id.web_link)
    WebView webLink;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        ButterKnife.bind(this);
        initView();
        initListenter();
    }

    protected void initView() {
        String link = getIntent().getStringExtra("URL");
        WebSettings webSettings = webLink.getSettings();
        webSettings.setSaveFormData(false);
        webSettings.setSavePassword(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webLink.setVerticalScrollBarEnabled(false);
        webLink.setHorizontalScrollBarEnabled(false);
        webLink.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    progressbar.setVisibility(View.GONE);
                } else {
                    if (progressbar != null && progressbar.getVisibility() == View.GONE) {
                        progressbar.setVisibility(View.VISIBLE);
                        progressbar.setProgress(newProgress);
                    }
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
        webLink.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webLink.loadUrl(link);
    }

    protected void initListenter() {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webLink.canGoBack()) {
            webLink.goBack();
            webLink.clearHistory();
            return true;
        } else {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webLink != null) {
            ViewGroup viewGroup = (ViewGroup) webLink.getParent();
            viewGroup.removeView(webLink);
            webLink.removeAllViews();
            webLink.destroy();
        }
        this.deleteDatabase("webview.db");
        this.deleteDatabase("webviewCache.db");
    }
}
