package com.mangu.velez_malagainfo.Informacion;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mangu.velez_malagainfo.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticiasActivity extends AppCompatActivity {
    @BindString(R.string.esperando) String esperando;
    @BindView(R.id.id_llayout2)
    LinearLayout idLlayout2;
    @BindString(R.string.url_noticias)
    String url;
    @BindString(R.string.url_base)
    String url_base;
    @BindString(R.string.no_conexion)
    String no_conexion;

    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        ButterKnife.bind(this);
        idLlayout2.setOrientation(LinearLayout.VERTICAL);
        idLlayout2.setDividerPadding(8);
        textView.setText(esperando);
        if (isNetworkAvailable(this.getApplicationContext())) {
            DownloadTask dw = new DownloadTask();
            dw.execute();
        } else {
            textView.setText(no_conexion);
            textView.setVisibility(View.VISIBLE);
        }
    }

    private class DownloadTask extends AsyncTask<Void, Void, Document> {

        @Override
        protected Document doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect(url).get();
                return doc;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Document document) {

            Element elements = document.getElementsByClass("items").first();
            for (Element e : elements.children()) {
                final TextView textView = new TextView(getApplicationContext());
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                textView.setText(e.text());
                textView.setTextColor(Color.BLACK);
                textView.setTypeface(null, Typeface.BOLD_ITALIC);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)textView.getLayoutParams();
                params.setMargins(0,20,0,0);
                textView.setLayoutParams(params);
                for (Element e_children : e.children()) {
                    final Element f_children = e_children.clone();
                    if (e_children.toString().contains("h2")) {
                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent browser = new Intent(Intent.ACTION_VIEW);
                                browser.setData(Uri.parse((url_base + (f_children.toString().split("href=\"")[1].split("\"")[0]))));
                                startActivity(browser);
                            }
                        });
                    }
                }

                idLlayout2.addView(textView);
            }
            textView.setVisibility(View.INVISIBLE);
        }
    }

    private boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}


