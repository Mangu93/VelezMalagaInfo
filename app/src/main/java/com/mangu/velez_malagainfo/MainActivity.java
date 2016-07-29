package com.mangu.velez_malagainfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mangu.velez_malagainfo.Informacion.CortesActivity;
import com.mangu.velez_malagainfo.Pueblos.VelezActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.btn_tlf)
    FancyButton btnTlf;
    @BindView(R.id.btn_playas)
    FancyButton btnPlayas;
    @BindView(R.id.btn_historia)
    FancyButton btnHistoria;
    @BindView(R.id.btn_transporte)
    FancyButton btnTransporte;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.imageView)
    ImageView imageView;

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
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                startActivity(intent);
                break;
            case R.id.torre:

                break;
            case R.id.caleta:

                break;
            case R.id.almayate:

                break;
            case R.id.benajarafe:

                break;
            case R.id.chilches:

                break;

            case R.id.lagos:

                break;

            default:

                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick({R.id.btn_tlf, R.id.btn_playas, R.id.btn_historia, R.id.btn_transporte, R.id.imageView, R.id.btn_cortes})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tlf:
                break;
            case R.id.btn_playas:
                break;
            case R.id.btn_historia:
                break;
            case R.id.btn_transporte:
                break;
            case R.id.imageView:
                break;
            case R.id.btn_cortes:
                startActivity(new Intent(getApplicationContext(), CortesActivity.class));
                break;
            default:
                Log.e("onClick",Integer.toString(view.getId()));
        }
    }
}
