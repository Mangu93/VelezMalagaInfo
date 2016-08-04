package com.mangu.velez_malagainfo.Pueblos;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mangu.velez_malagainfo.Mapa.MapsActivity;
import com.mangu.velez_malagainfo.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

public class VelezActivity extends AppCompatActivity {

    protected LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            //mLocationManager.removeUpdates(locationListener);
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };
    @BindView(R.id.btn_puntos)
    FancyButton btnPuntos;
    @BindView(R.id.btn_cercano)
    FancyButton btnCercano;
    @BindView(R.id.btn_comprar)
    FancyButton btnComprar;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindString(R.string.no_info)
    String no_info;
    @BindString(R.string.desarrollo)
    String desarrollo;
    @BindString(R.string.Velez) String velez;
    private String extra = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velez);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                setTitle(extras.getString("name"));
                extra = extras.getString("name");
            }
        } else {
            setTitle(savedInstanceState.getString("name"));
            extra = savedInstanceState.getString("name");
        }
    }

    @OnClick({R.id.btn_puntos, R.id.btn_cercano, R.id.btn_comprar, R.id.btn_comer})
    public void onClick(View view) {
        Intent intent;
        Context context = getApplicationContext();
        switch (view.getId()) {
            case R.id.btn_puntos:
                intent = new Intent(context, MapsActivity.class);
                if (extra != null) {
                    intent.putExtra("json", extra);
                    startActivity(intent);
                } else {
                    Log.e("JSON", "Error con el extra");
                }
                break;
            case R.id.btn_cercano:
                if (extra.equals(velez)) {
                    intent = new Intent(context, MapsActivity.class);
                    intent.putExtra("json", "velez_transporte");
                    startActivity(intent);
                } else if (extra.contains("Torre")) {
                    intent = new Intent(context, MapsActivity.class);
                    intent.putExtra("json", "torre_transporte");
                    startActivity(intent);
                } else if (extra.contains("Almay")) {
                    intent = new Intent(context, MapsActivity.class);
                    intent.putExtra("json", "almayate_transporte");
                    startActivity(intent);
                } else if (extra.contains("Caleta")) {
                    intent = new Intent(context, MapsActivity.class);
                    intent.putExtra("json", "caleta_transporte");
                    startActivity(intent);
                }else  if (extra.contains("Benajar")) {
                    intent = new Intent(context, MapsActivity.class);
                    intent.putExtra("json", "benajarafe_transporte");
                    startActivity(intent);
                }else if (extra.equals("Chilches")) {
                    intent = new Intent(context, MapsActivity.class);
                    intent.putExtra("json", "chilches_transporte");
                    startActivity(intent);
                }else if(extra.equals("Lagos")) {
                    intent = new Intent(context, MapsActivity.class);
                    intent.putExtra("json", "lagos_transporte");
                    startActivity(intent);
                }

                break;
            case R.id.btn_comprar:
                intent = new Intent(context, MapsActivity.class);
                if (extra.equals(velez)) {
                    intent.putExtra("json", "velez_compras");
                } else if (extra.contains("Torre")) {
                    intent.putExtra("json", "torre_compras");
                } else if (extra.contains("Almay")) {
                    intent.putExtra("json", "almayate_compras");
                } else if (extra.contains("Caleta")) {
                    intent.putExtra("json", "caleta_compras");
                }else if (extra.contains("Benajar")) {
                    intent.putExtra("json", "benajarafe_compras");
                }else if (extra.equals("Chilches")) {
                    intent.putExtra("json", "chilches_compras");
                }else if(extra.equals("Lagos")) {
                    Toast.makeText(context, no_info, Toast.LENGTH_LONG).show();
                    break;
                }
                startActivity(intent);
                break;
            case R.id.btn_comer:
                Toast.makeText(context, desarrollo, Toast.LENGTH_LONG).show();
                break;
        }
    }


}
