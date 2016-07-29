package com.mangu.velez_malagainfo.Pueblos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.mangu.velez_malagainfo.Mapa.MapsActivity;
import com.mangu.velez_malagainfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

public class VelezActivity extends AppCompatActivity {

    @BindView(R.id.btn_puntos)
    FancyButton btnPuntos;
    @BindView(R.id.btn_cercano)
    FancyButton btnCercano;
    @BindView(R.id.btn_comprar)
    FancyButton btnComprar;
    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velez);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_puntos, R.id.btn_cercano, R.id.btn_comprar, R.id.imageView})
    public void onClick(View view) {
        Intent intent;
        Context context = getApplicationContext();
        switch (view.getId()) {
            case R.id.btn_puntos:
                intent = new Intent(context, MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_cercano:
                break;
            case R.id.btn_comprar:
                break;
            case R.id.imageView:
                break;
        }
    }
}
