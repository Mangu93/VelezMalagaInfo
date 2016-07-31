package com.mangu.velez_malagainfo.Mapa;

import android.location.LocationManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mangu.velez_malagainfo.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *     private static final String TAG = "MapActivity";
 private GoogleMap mMap;
 private LocationManager mLocationManager;
 private List<Marker> mMarkerList;
 private int mCounter = 0;
 private List<String> mStringMarkerList;
 private RequestQueue mRequestQueue;

 @Override
 public void onMapReady(GoogleMap googleMap) {

 }

 @Override
 protected void onCreate(@Nullable Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 mStringMarkerList = new ArrayList<>();
 mMarkerList = new ArrayList<>();
 mRequestQueue = Volley.newRequestQueue(getApplicationContext());
 mLocationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
 setContentView(R.layout.activity_map);
 FragmentManager fManager = getSupportFragmentManager();
 Fragment fragment = fManager.findFragmentById(R.id.mapview);
 SupportMapFragment mapFragment = (SupportMapFragment) fragment;
 mapFragment.getMapAsync(this);
 }
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private LocationManager mLocationManager;
    private List<Marker> mMarkerList;
    private int mCounter = 0;
    private List<String> mStringMarkerList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStringMarkerList = new ArrayList<>();
        mMarkerList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mLocationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        setContentView(R.layout.activity_maps);
        FragmentManager fManager = getSupportFragmentManager();
        Fragment fragment = fManager.findFragmentById(R.id.map);
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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
