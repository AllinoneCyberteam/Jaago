package com.example.nani.jagoo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Deschealth extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desc_health);
        webView=findViewById(R.id.webView);
        Bundle bundle=getIntent().getExtras();
        String url=bundle.getString("url");
        webView.loadUrl(url);
    }
}
