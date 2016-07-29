package com.mangu.velez_malagainfo.Modelo;

import android.util.Pair;

import com.mangu.velez_malagainfo.R;

import butterknife.BindString;

/**
 * Created by Adrian Portillo on 29/07/2016.
 */
public class Prediccion {
    private String fecha;
    private String sensacion_termica;
    private String viento_mañana;
    private String viento_tarde;
    private String oleaje_mañana;
    private String oleaje_tarde;
    private int uv_maximo;//10 es muy alto
    private int temperatura_agua;
    private int temperatura_maxima;
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
    public Prediccion() {

    }
    public String presentacion() {
        return dia + " : " + getFecha() + ". "+maxima+": "+getTemperatura_maxima()+". "+agua+": "+getTemperatura_agua()
                +". "+sensacion+": "+getSensacion_termica()+". "+uv_max+": "+getUv_maximo()+". "+oleaje+": "+mañana + getOleaje_mañana()
                +" , "+ tarde + getOleaje_tarde() + ". "+viento+": "+mañana+ getViento_mañana() + ", "+tarde + getViento_tarde() +". ";
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        String year = fecha.substring(0,4);
        String month = fecha.substring(4,6);
        String day = fecha.substring(6);
        this.fecha = day+"-"+month+"-"+year;
    }

    public String getOleaje_mañana() {
        return oleaje_mañana;
    }

    public void setOleaje_mañana(String oleaje_mañana) {
        this.oleaje_mañana = oleaje_mañana;
    }

    public String getOleaje_tarde() {
        return oleaje_tarde;
    }

    public void setOleaje_tarde(String oleaje_tarde) {
        this.oleaje_tarde = oleaje_tarde;
    }

    public String getSensacion_termica() {
        return sensacion_termica;
    }

    public void setSensacion_termica(String sensacion_termica) {
        this.sensacion_termica = sensacion_termica;
    }

    public int getTemperatura_agua() {
        return temperatura_agua;
    }

    public void setTemperatura_agua(int temperatura_agua) {
        this.temperatura_agua = temperatura_agua;
    }

    public int getUv_maximo() {
        return uv_maximo;
    }

    public void setUv_maximo(int uv_maximo) {
        this.uv_maximo = uv_maximo;
    }

    public int getTemperatura_maxima() {
        return temperatura_maxima;
    }

    public void setTemperatura_maxima(int temperatura_maxima) {
        this.temperatura_maxima = temperatura_maxima;
    }

    public String getViento_mañana() {
        return viento_mañana;
    }

    public void setViento_mañana(String viento_mañana) {
        this.viento_mañana = viento_mañana;
    }

    public String getViento_tarde() {
        return viento_tarde;
    }

    public void setViento_tarde(String viento_tarde) {
        this.viento_tarde = viento_tarde;
    }


}
