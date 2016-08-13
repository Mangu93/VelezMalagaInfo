package com.mangu.velez_malagainfo.Informacion;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mangu.velez_malagainfo.R;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

public class TelefonosActivity extends AppCompatActivity {

    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindArray(R.array.atencionciudadana)
    String[] atencion;
    @BindView(R.id.textView2)
    TextView textView2;
    private final String [] tlf_taxis = {"Vélez-Málaga", "Torre del Mar", "Torre del Mar"};
    private final String [] pueblos = {"Vélez-Málaga", "Torre del Mar", "Caleta de Velez", "Almayate", "Benajarafe", "Cajiz", "Chilches", "Mezquitilla"};
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.linearLayout1)
    LinearLayout linearLayout1;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @BindArray(R.array.taxis_tlf) String[] taxis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefonos);
        ButterKnife.bind(this);
        linearLayout1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        for (int i = 0; i < atencion.length; i++) {
            final String tlf = atencion[i];
            final String name = pueblos[i];
            FancyButton tlfBtn = new FancyButton(this);
            tlfBtn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            tlfBtn.setText(name + ": " + tlf);
            tlfBtn.setBackgroundColor(Color.parseColor("#3b5998"));
            tlfBtn.setFocusBackgroundColor(Color.parseColor("#5474b8"));
            tlfBtn.setTextSize(17);
            tlfBtn.setRadius(5);
            tlfBtn.setIconPosition(FancyButton.POSITION_LEFT);
            tlfBtn.setFontIconSize(30);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)tlfBtn.getLayoutParams();
            params.setMargins(0,20,0,0);
            tlfBtn.setLayoutParams(params);
            tlfBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent telephone = new Intent(Intent.ACTION_DIAL);
                    telephone.setData(Uri.parse("tel:"+tlf));
                    startActivity(telephone);
                }
            });
            linearLayout1.addView(tlfBtn);
        }
        for (int j = 0; j < taxis.length; j++) {
            final String tlf = taxis[j];
            final String name = tlf_taxis[j];
            FancyButton tlfBtn = new FancyButton(this);
            tlfBtn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            tlfBtn.setText(name + ": "+tlf);
            tlfBtn.setBackgroundColor(Color.parseColor("#a4c639"));
            tlfBtn.setFocusBackgroundColor(Color.parseColor("#bfe156"));
            tlfBtn.setTextSize(17);
            tlfBtn.setRadius(5);
            tlfBtn.setIconPosition(FancyButton.POSITION_LEFT);
            tlfBtn.setFontIconSize(30);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)tlfBtn.getLayoutParams();
            params.setMargins(0,20,0,0);
            tlfBtn.setLayoutParams(params);
            tlfBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent telephone = new Intent(Intent.ACTION_DIAL);
                    telephone.setData(Uri.parse("tel:"+tlf));
                    startActivity(telephone);
                }
            });
            linearLayout2.addView(tlfBtn);
        }
    }


}
