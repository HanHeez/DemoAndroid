package com.gtv.hanhee.readrss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DocBaoActivity extends AppCompatActivity {
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_bao);

        Intent intent = getIntent();
        String link = intent.getStringExtra("linktintuc");

        webview = (WebView) findViewById(R.id.webViewTintuc);
        webview.loadUrl(link);
        webview.setWebViewClient(new WebViewClient());
    }
}
