package com.mangu.velez_malagainfo.Informacion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    @BindString(R.string.url_trafico) String url;
    Document doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cortes);
        ButterKnife.bind(this);
        idLlayout.setOrientation(LinearLayout.VERTICAL);
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        try{
            doc = Jsoup.connect(url).get();
            Element elements = doc.getElementsByClass("cuerpo").first();
            for(Element e : elements.children()) {
                textView.setText(e.toString());
                idLlayout.addView(textView);
            }
        }catch(IOException ex) {
            Log.e("JSOUP", ex.toString());
        }
    }
}
