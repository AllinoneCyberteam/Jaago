package com.example.dell.sports;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class DescSports extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_sports);
        webView=findViewById(R.id.webView);
        Bundle bundle=getIntent().getExtras();
        String url=bundle.getString("url");
        webView.loadUrl(url);
    }
}
