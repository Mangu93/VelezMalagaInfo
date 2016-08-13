package com.mangu.velez_malagainfo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mangu.velez_malagainfo.Informacion.CortesActivity;
import com.mangu.velez_malagainfo.Informacion.HistoriaActivity;
import com.mangu.velez_malagainfo.Informacion.NoticiasActivity;
import com.mangu.velez_malagainfo.Informacion.PlayasActivity;
import com.mangu.velez_malagainfo.Informacion.TelefonosActivity;
import com.mangu.velez_malagainfo.Pueblos.VelezActivity;

import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    protected DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.btn_tlf)
    FancyButton btnTlf;
    @BindString(R.string.enviar) String enviar;
    @BindString(R.string.problema) String problema;
    @BindView(R.id.btn_playas)
    FancyButton btnPlayas;
    @BindView(R.id.btn_historia)
    FancyButton btnHistoria;
    @BindView(R.id.btn_noticias)
    FancyButton btnTransporte;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindString(R.string.creador) String creador;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindString(R.string.seleccionar)
    String seleccione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showAlert();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAlert() {
        Locale current = getResources().getConfiguration().locale;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (current.toString().equals("es_es") || current.toString().equals("es_ES")) {

            builder.setMessage(seleccione)
                    .setCancelable(false)
                    .setPositiveButton("Inglés", new DialogInterface.OnClickListener() {
                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            Locale locale = new Locale("en", "us");
                            Locale.setDefault(locale);
                            changeLocale(locale);
                        }
                    })
                    .setNegativeButton("Alemán", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            Locale locale = new Locale("de", "DE");
                            Locale.setDefault(locale);
                            changeLocale(locale);
                        }
                    });

        } else if (current.toString().contains("en_")) {
            builder.setMessage(seleccione)
                    .setCancelable(false)
                    .setPositiveButton("Spanish", new DialogInterface.OnClickListener() {
                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            Locale locale = new Locale("es", "ES");
                            Locale.setDefault(locale);
                            changeLocale(locale);
                        }
                    })
                    .setNegativeButton("German", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            Locale locale = new Locale("de", "DE");
                            Locale.setDefault(locale);
                            changeLocale(locale);

                        }
                    });
        }else if(current.toString().contains("de")) {
            builder.setMessage(seleccione)
                    .setCancelable(false)
                    .setPositiveButton("Spanisch", new DialogInterface.OnClickListener() {
                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            Locale locale = new Locale("es", "ES");
                            Locale.setDefault(locale);
                            changeLocale(locale);
                        }
                    })
                    .setNegativeButton("Englisch", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            Locale locale = new Locale("en", "US");
                            Locale.setDefault(locale);
                            changeLocale(locale);

                        }
                    });
        }
        final AlertDialog alert = builder.create();
        alert.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    alert.dismiss();
                }
                return true;
            }
        });

        alert.show();
    }

    private void changeLocale(Locale locale) {
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLayoutDirection(config.locale);
        }
        getApplicationContext().getResources().updateConfiguration(config, null);
        recreate();
        /*Resources res = getApplicationContext().getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf,dm);*/
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent intent;
        Context context = getApplicationContext();
        int id = item.getItemId();
        switch (id) {
            case R.id.velez:
                intent = new Intent(context, VelezActivity.class);
                intent.putExtra("name", "Vélez-Málaga");
                startActivity(intent);
                break;
            case R.id.torre:
                intent = new Intent(context, VelezActivity.class);
                intent.putExtra("name", "Torre del Mar");
                startActivity(intent);

                break;
            case R.id.caleta:
                intent = new Intent(context, VelezActivity.class);
                intent.putExtra("name", "Caleta de Vélez");
                startActivity(intent);

                break;
            case R.id.almayate:
                intent = new Intent(context, VelezActivity.class);
                intent.putExtra("name", "Almayate");
                startActivity(intent);

                break;
            case R.id.benajarafe:
                intent = new Intent(context, VelezActivity.class);
                intent.putExtra("name", "Benajarafe");
                startActivity(intent);

                break;
            case R.id.chilches:
                intent = new Intent(context, VelezActivity.class);
                intent.putExtra("name", "Chilches");
                startActivity(intent);

                break;
            case R.id.lagos:
                intent = new Intent(context, VelezActivity.class);
                intent.putExtra("name", "Lagos");
                startActivity(intent);

                break;
            case R.id.nav_ayuda:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",creador, null));
                intent.putExtra(Intent.EXTRA_SUBJECT, problema);
                startActivity(Intent.createChooser(intent, enviar));
            default:

                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick({R.id.btn_tlf, R.id.btn_playas, R.id.btn_historia, R.id.btn_noticias, R.id.imageView, R.id.btn_cortes})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tlf:
                startActivity(new Intent(getApplicationContext(), TelefonosActivity.class));
                break;
            case R.id.btn_playas:
                startActivity(new Intent(getApplicationContext(), PlayasActivity.class));
                break;
            case R.id.btn_historia:
                startActivity(new Intent(getApplicationContext(), HistoriaActivity.class));
                break;
            case R.id.btn_noticias:
                startActivity(new Intent(getApplicationContext(), NoticiasActivity.class));
                break;
            case R.id.imageView:
                break;
            case R.id.btn_cortes:
                startActivity(new Intent(getApplicationContext(), CortesActivity.class));
                break;
            default:
                Log.e("onClick", Integer.toString(view.getId()));
        }
    }
}
