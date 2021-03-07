package com.example.fishingapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.fishingapp.R;

public class HelpActivity extends AppCompatActivity {

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        url = getIntent().getStringExtra("Ayuda");

        Toolbar toolbar = findViewById(R.id.helpToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.Help);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebView webView;
        setContentView(R.layout.activity_help);
        webView = findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

    }
}