package com.mangu.velez_malagainfo.Mapa;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mangu.velez_malagainfo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindString;
import butterknife.ButterKnife;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private final LocationListener locationListener = new LocationListener() {
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
    private GoogleMap mMap;
    private LocationManager mLocationManager;
    private List<Marker> mMarkerList;
    private int mCounter = 0;
    private JSONObject jsonMarkers;
    @BindString(R.string.Velez) String velez;
    @BindString(R.string.Torre) String torre;
    @BindString(R.string.Almayate)
    String almayate;
    @BindString(R.string.Caleta)
    String caleta;
    @BindString(R.string.Benajarafe)
    String benajarafe;
    @BindString(R.string.Chilches)
    String chilches;
    @BindString(R.string.Lagos)
    String lagos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String extra = "";
        jsonMarkers = new JSONObject();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                extra = extras.getString("json");
            }
        } else {
            extra = (savedInstanceState.getString("json"));
        }
        mMarkerList = new ArrayList<>();
        mLocationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
            if (extra.equals(velez)) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.velez);
                jsonMarkers = parser(is);
            }else if(extra.equals("velez_compras")) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.velez_compras);
                jsonMarkers = parser(is);
            }else if(extra.equals("velez_transporte")) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.velez_transporte);
                jsonMarkers = parser(is);
            }else if(extra.equals(torre)) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.torre);
                jsonMarkers = parser(is);
            }else if(extra.equals("torre_transporte")) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.torre_transporte);
                jsonMarkers = parser(is);
            }else if(extra.equals("torre_compras")) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.torre_compras);
                jsonMarkers = parser(is);
            }else if(extra.equals(almayate)) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.almayate);
                jsonMarkers = parser(is);
            }else if(extra.equals("almayate_transporte")) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.almayate_transporte);
                jsonMarkers = parser(is);
            }else if(extra.equals("almayate_compras")) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.almayate_compras);
                jsonMarkers = parser(is);
            }else if(extra.equals(caleta)) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.caleta);
                jsonMarkers = parser(is);
            }else if(extra.equals("caleta_transporte")) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.caleta_transporte);
                jsonMarkers = parser(is);
            }else if(extra.equals("caleta_compras")) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.caleta_compras);
                jsonMarkers = parser(is);
            }else if(extra.equals(benajarafe)) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.benajarafe);
                jsonMarkers = parser(is);
            }else if(extra.equals("benajarafe_transporte")) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.benajarafe_transporte);
                jsonMarkers = parser(is);
            }else if(extra.equals("benajarafe_compras")) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.benajarafe_compras);
                jsonMarkers = parser(is);
            }else if(extra.equals(chilches)) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.chilches);
                jsonMarkers = parser(is);
            }else if(extra.equals("chilches_transporte")) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.chilches_transporte);
                jsonMarkers = parser(is);
            }else if(extra.equals("chilches_compras")) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.chilches_compras);
                jsonMarkers = parser(is);
            }else if(extra.equals(lagos)) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.lagos);
                jsonMarkers = parser(is);
            }else if(extra.equals("lagos_transporte")) {
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.lagos_transporte);
                jsonMarkers = parser(is);
            }

        FragmentManager fManager = getSupportFragmentManager();
        Fragment fragment = fManager.findFragmentById(R.id.mapview);
        SupportMapFragment mapFragment = (SupportMapFragment) fragment;
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15));
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
                mMap.animateCamera(CameraUpdateFactory.zoomTo(35), 2000, null);
                marker.showInfoWindow();
                return false;
            }
        });

        try {
            JSONObject markers = jsonMarkers.getJSONObject("markers");
            Iterator<String> keys = markers.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                String value = markers.getString(key);
                String[] splitted = value.split(",");
                LatLng latLng = new LatLng(Double.parseDouble(splitted[0]), Double.parseDouble(splitted[1]));
                Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title(key));
                mMarkerList.add(marker);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        moveCamera(mMarkerList.get(0));
    }


    public void onClickNext(@SuppressWarnings("UnusedParameters") View view) {
        int next = mCounter % mMarkerList.size();
        if (next < mMarkerList.size()) {
            moveCamera(next);
            mCounter++;
        }
    }

    private void moveCamera(int next) {
        Marker next_marker = mMarkerList.get(next);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(next_marker.getPosition(), 15));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        next_marker.showInfoWindow();
    }

    private void moveCamera(Marker marker) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        marker.showInfoWindow();
    }

    public void onClickFocus(@SuppressWarnings("UnusedParameters") View view) {
        try {
            List<String> providers = mLocationManager.getProviders(true);
            Location bestLocation = null;
            for (String provider : providers) {
                Location l = mLocationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    bestLocation = l;
                }
            }
            if (bestLocation == null) {
                Criteria criteria = new Criteria();
                criteria.setAccuracy(Criteria.ACCURACY_FINE);
                criteria.setPowerRequirement(Criteria.POWER_HIGH);
                LocationListener lL = locationListener;
                String provider = mLocationManager.getBestProvider(criteria, true);
                mLocationManager.requestLocationUpdates(provider, 0, 0, lL, getMainLooper());
                bestLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
            if (bestLocation != null) {
                LatLng newLatLng = new LatLng(bestLocation.getLatitude(), bestLocation.getLongitude());
                Marker actualLocation = mMap.addMarker(new MarkerOptions().position(newLatLng).title("Usted está aquí").snippet("Ultima localización conocida").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_localizar)));
                actualLocation.showInfoWindow();
                mMap.moveCamera(CameraUpdateFactory.newLatLng(newLatLng));
            } else {
                Toast.makeText(MapsActivity.this, "Su dispositivo no tiene localizaciones en caché", Toast.LENGTH_SHORT).show();
                Log.e("NoiseActivity", "No hay localizaciones en caché");
            }
        } catch (SecurityException e) {
            Log.e("SecurityException", e.getLocalizedMessage());
        } catch (NullPointerException ex) {
            Log.e("NullPointerException", ex.getLocalizedMessage());
        }
    }
    private JSONObject parser(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("Text Data", byteArrayOutputStream.toString());
        try {
            JSONObject jObject = new JSONObject(
                    byteArrayOutputStream.toString());
            return jObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }
}
