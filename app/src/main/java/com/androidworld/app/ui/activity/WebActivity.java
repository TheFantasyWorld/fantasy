package com.androidworld.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;

import butterknife.Bind;

@SuppressWarnings("all")
public class WebActivity extends BaseSwipeBackActivity {

    @Bind(R.id.web_view)
    WebView mWebView;

    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;

    public static void openWebActivity(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWebView();
        mWebView.loadUrl(getIntent().getStringExtra("url"));
    }

    private void initWebView() {
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.INVISIBLE);
                } else {
                    if (View.INVISIBLE == mProgressBar.getVisibility()) {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                    mProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

        });
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return NO_BASE_CONTENT_VIEW;
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_web;
    }
}
