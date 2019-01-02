package com.jaago.jaago;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class DescNews extends AppCompatActivity {
private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_news);
        webView= (WebView) findViewById(R.id.webView);
        Bundle bundle=getIntent().getExtras();
        String url=bundle.getString("url");
        webView.loadUrl(url);
    }
}
