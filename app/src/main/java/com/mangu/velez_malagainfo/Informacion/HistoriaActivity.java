package com.mangu.velez_malagainfo.Informacion;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.mangu.velez_malagainfo.R;

import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoriaActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView webView;
    String en_wiki="https://en.m.wikipedia.org/wiki/V%C3%A9lez-M%C3%A1laga";
    String es_wiki="https://es.m.wikipedia.org/wiki/V%C3%A9lez-M%C3%A1laga";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia);
        ButterKnife.bind(this);

        if(isNetworkAvailable(getApplicationContext())) {
            if (Locale.getDefault().getDisplayName().contains("English")) {
                webView.loadUrl(en_wiki);
            } else {
                webView.loadUrl(es_wiki);
            }
        }
    }
    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
