package com.devil.organo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.devil.organo.R;
import com.devil.organo.helpers.SingleShotLocationProvider;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap googleMap;
    private Marker userMarker;
    private List<Double> latitude,longitude;
    private List<String> name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        if(this.getSupportActionBar()!=null)
            this.getSupportActionBar().hide();
        latitude = new ArrayList<>();
        longitude = new ArrayList<>();
        name = new ArrayList<>();
        MapView mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
    }

    private void showMarkers() {
        getDummyData();
        for (int i=0; i<latitude.size();i++){
            googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude.get(i),longitude.get(i))).title(name.get(i)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        }
    }

    public void getDummyData() {
        latitude.add(20.353440);longitude.add(85.815410);name.add("KIIMS");
        latitude.add(20.231199);longitude.add(85.775096);name.add("AIMS");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setTrafficEnabled(true);
        googleMap.setBuildingsEnabled(true);

        showMarkers();
        MarkerOptions options=new MarkerOptions().position(new LatLng(20.2961,85.8245)).title("Current Location");
        userMarker = googleMap.addMarker(options);

        requestLocation();
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        ImageView locate = findViewById(R.id.locate);
        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLocation();
            }
        });
    }

    protected void requestLocation(){
        SingleShotLocationProvider.requestSingleUpdate(this,
                new SingleShotLocationProvider.LocationCallback() {
                    @Override public void onNewLocationAvailable(SingleShotLocationProvider.GPSCoordinates location) {
                        LatLng CURRENT_LOCATION = new LatLng(location.getLatitude(), location.getLongitude());
                        userMarker.setPosition(CURRENT_LOCATION);
                        userMarker.setVisible(true);
                        userMarker.showInfoWindow();
                        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(CURRENT_LOCATION,15);
                        googleMap.animateCamera(update);
                    }
                });
    }
}
