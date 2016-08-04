package com.mangu.velez_malagainfo.Informacion;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
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

public class CortesActivity extends AppCompatActivity {

    @BindView(R.id.id_llayout)
    LinearLayout idLlayout;
    @BindString(R.string.url_trafico)
    String url;
    @BindView(R.id.textView)
    TextView textView;
    @BindString(R.string.no_conexion) String no_conexion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cortes);
        ButterKnife.bind(this);
        idLlayout.setOrientation(LinearLayout.VERTICAL);
        idLlayout.setDividerPadding(3);
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
            Element elements = document.getElementsByClass("cuerpo").first();
            for (Element e : elements.children()) {
                TextView textView = new TextView(getApplicationContext());
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                textView.setText(e.text());
                textView.setTextColor(Color.BLACK);
                textView.setTypeface(null, Typeface.BOLD_ITALIC);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)textView.getLayoutParams();
                params.setMargins(0,20,0,0);
                textView.setLayoutParams(params);

                textView.setText(e.text());
                idLlayout.addView(textView);
                if (e.text().contains("NOTA")) {
                    break; //SHITTY SOLUTION
                }
            }
            textView.setVisibility(View.INVISIBLE);
        }
    }

    private boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
