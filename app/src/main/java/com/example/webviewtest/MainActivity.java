package com.example.webviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        initWebview();
        initBottomFooter();
    }

    private WebView mWebView;

    private void initWebview() {
        String url = getString(R.string.url);

        CookieManager.getInstance().setAcceptCookie(true);
        mWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        WebView myWebView = (WebView) findViewById(R.id.webview);
    }

    private EditText mUrlEditText;
    private Button mSetUrlButton;

    private void initBottomFooter() {
        mUrlEditText = (EditText) findViewById(R.id.urlEditText);
        mSetUrlButton = (Button) findViewById(R.id.setUrlButton);
        mSetUrlButton.setOnClickListener(this);
    }

    String mUrl;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.setUrlButton:
                mUrl = mUrlEditText.getText().toString();
                openUrl();
                break;
        }

    }

    private void openUrl() {
        mWebView.loadUrl(mUrl);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}
