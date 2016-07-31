package com.mangu.velez_malagainfo.Modelo;

import android.util.Pair;

import com.mangu.velez_malagainfo.R;

import java.util.Locale;

import butterknife.BindString;
import butterknife.ButterKnife;

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
    String dia;
    String agua;
    String maxima;
    String oleaje;
    String viento;
    String sensacion;
    String uv_max;
    String cielo;
    String mañana;
    String tarde;
    public Prediccion() {

    }

    public Prediccion(String dia, String agua, String maxima, String oleaje, String viento, String sensacion, String uv_max, String cielo, String mañana, String tarde) {
        this.dia=dia;
        this.agua=agua;
        this.maxima=maxima;
        this.oleaje=oleaje;
        this.viento=viento;
        this.sensacion=sensacion;
        this.uv_max=uv_max;
        this.cielo=cielo;
        this.mañana=mañana;
        this.tarde=tarde;
    }

    public String presentacion() {
        return dia + " : " + getFecha() + ". "+maxima+": "+getTemperatura_maxima()+". "+agua+": "+getTemperatura_agua()
                +". "+sensacion+": "+getSensacion_termica()+". "+uv_max+": "+getUv_maximo()+". "+oleaje+": "+mañana +" -> " + getOleaje_mañana()
                +" , "+ tarde + " -> " + getOleaje_tarde() + ". "+viento+": "+mañana+" " +getViento_mañana() + ", "+tarde + " " + getViento_tarde() +". ";
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
        if(oleaje_mañana.contains("bil")){
            if(Locale.getDefault().getDisplayName().contains("English")){
                this.oleaje_mañana="Weak";
            }else{
                this.oleaje_mañana="Debil";
            }
        }
        else{
            if(Locale.getDefault().getDisplayName().contains("English")){
                this.oleaje_mañana="Strong";
            }else{
                this.oleaje_mañana="Fuerte";
            }
        }
    }

    public String getOleaje_tarde() {
        if(oleaje_tarde==null) return "No info";
        return oleaje_tarde;
    }

    public void setOleaje_tarde(String oleaje_tarde) {
        if(oleaje_tarde.contains("bil")){
            if(Locale.getDefault().getDisplayName().contains("English")){
                this.oleaje_mañana="Weak";
            }else{
                this.oleaje_mañana="Debil";
            }
        }
        else{
            if(Locale.getDefault().getDisplayName().contains("English")){
                this.oleaje_mañana="Strong";
            }else{
                this.oleaje_mañana="Fuerte";
            }
        }
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
