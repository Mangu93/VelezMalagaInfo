package com.mangu.velez_malagainfo.Informacion;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mangu.velez_malagainfo.Modelo.Prediccion;
import com.mangu.velez_malagainfo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class PlayasActivity extends AppCompatActivity {
    @BindString(R.string.xml_tiempo)
    String xml;
    @BindView(R.id.tv_hoy)
    TextView tvHoy;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindString(R.string.dia) String dia;
    @BindString(R.string.temp_agua) String agua;
    @BindString(R.string.prediccion) String prediccion;
    @BindString(R.string.temp_maxima) String maxima;
    @BindString(R.string.oleaje) String oleaje;
    @BindString(R.string.viento) String viento;
    @BindString(R.string.sensacion_termica) String sensacion;
    @BindString(R.string.uv_max) String uv_max;
    @BindString(R.string.estado_cielo) String cielo;
    @BindString(R.string.mañana) String mañana;
    @BindString(R.string.tarde) String tarde;
    @BindString(R.string.no_conexion) String no_conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playas);
        ButterKnife.bind(this);
        if(isNetworkAvailable(this.getApplicationContext())) {
            Observable<String> fetchXML = Observable.create(new Observable.OnSubscribe<String>() {

                @Override
                public void call(Subscriber<? super String> subscriber) {
                    try {
                        String xml_downloaded = getURLContent(xml);
                        subscriber.onNext(xml_downloaded);
                        subscriber.onCompleted();
                    } catch (Exception e) {
                        subscriber.onError(e); // In case there are network errors
                    }
                }
            });
            fetchXML.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).
                    subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject = XML.toJSONObject(s);
                                JSONObject prediccion = jsonObject.getJSONObject("playa").getJSONObject("prediccion");
                                JSONArray array = prediccion.getJSONArray("dia");
                                JSONObject hoy = array.getJSONObject(0);
                                JSONObject futuro = array.getJSONObject(1);
                                String tv_hoy = generarTV(hoy);
                                String tv_futuro = generarTV(futuro);
                                tvHoy.setText(Html.fromHtml(tv_hoy+"<br>"));
                                tvNext.setText(Html.fromHtml(tv_futuro+"<br>"));
                                tvNext.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT));
                                tvHoy.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT));
                            } catch (JSONException e) {
                                Log.e("Observable", e.toString());
                            }
                        }
                    });
        }else{
            tvNext.setVisibility(View.INVISIBLE);
            tvHoy.setText(no_conexion);
        }
    }

    private String generarTV(JSONObject hoy) throws JSONException {
        Prediccion prediccion = new Prediccion(dia, agua, maxima, oleaje, viento, sensacion, uv_max, cielo, mañana, tarde);
        String t_maxima = hoy.getJSONObject("t_maxima").toString().split("\\:")[1].replace("}", "");
        prediccion.setTemperatura_maxima(Integer.parseInt(t_maxima));
        prediccion.setFecha(hoy.get("fecha").toString());
        prediccion.setTemperatura_agua(Integer.parseInt(hoy.getJSONObject("t_agua").toString().split("\\:")[1].replace("}", "")));
        prediccion.setOleaje_mañana(hoy.getJSONObject("oleaje").get("descripcion1").toString());
        prediccion.setOleaje_tarde(hoy.getJSONObject("oleaje").get("descripcion2").toString());
        prediccion.setViento_mañana(hoy.getJSONObject("viento").get("descripcion1").toString());
        prediccion.setViento_tarde(hoy.getJSONObject("viento").get("descripcion2").toString());
        prediccion.setSensacion_termica(hoy.getJSONObject("s_termica").get("descripcion1").toString());
        prediccion.setUv_maximo(Integer.parseInt(hoy.getJSONObject("uv_max").toString().split("\\:")[1].replace("}", "")));
        return prediccion.presentacion();
    }
    private boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
    private static String getURLContent(String p_sURL) {
        URL oURL;
        URLConnection oConnection;
        BufferedReader oReader;
        String sLine;
        StringBuilder sbResponse;
        String sResponse = null;

        try {
            oURL = new URL(p_sURL);
            oConnection = oURL.openConnection();
            oReader = new BufferedReader(new InputStreamReader(oConnection.getInputStream()));
            sbResponse = new StringBuilder();

            while ((sLine = oReader.readLine()) != null) {
                sbResponse.append(sLine);
            }

            sResponse = sbResponse.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sResponse;
    }


}