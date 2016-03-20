package com.kartech.locationtracker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements LocationListener {

    // Class to do operations on the Map
    GoogleMap googleMap;
    double curlat;
    double curlong;
    Marker markermylocation;
    MarkerOptions markerOptionsmylocaiton;
    int firsttime = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // setuping locatiomanager to perfrom location related operations
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Requesting locationmanager for location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1,
                1, this);
        // To get map from MapFragment from layout
        googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                R.id.map)).getMap();

        // To change the map type to Satellite
        // googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // To show our current location in the map with dot
         googleMap.setMyLocationEnabled(true);

        // To listen action whenever we click on the map
        googleMap.setOnMapClickListener(new OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                /*
                 * LatLng:Class will give us selected position lattigude and
				 * longitude values
				 */
                Toast.makeText(getApplicationContext(), latLng.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {
        /*// To get lattitude value from location object
        double latti = location.getLatitude();
        // To get longitude value from location object
        double longi = location.getLongitude();

        // To hold lattitude and longitude values
        LatLng position = new LatLng(latti, longi);

        // Creating object to pass our current location to the map
        MarkerOptions markerOptions = new MarkerOptions();
        // To store current location in the markeroptions object
        markerOptions.position(position);

        // Zooming to our current location with zoom level 17.0f
        googleMap.animateCamera(CameraUpdateFactory
                .newLatLngZoom(position, 17f));

        // adding markeroptions class object to the map to show our current
        // location in the map with help of default marker
        googleMap.addMarker(markerOptions);*/

        curlat = location.getLatitude();
        curlong = location.getLongitude();

        if (markermylocation != null)
        {
            markermylocation.remove();
        }

        curlat = location.getLatitude();
        curlong = location.getLongitude();
        myLocation(curlat, curlong, "Kishor", "", 17f);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    private void myLocation(double lat, double lng, String name, String url, float zoom)
    {
//        if(firsttime  == 1)
        {
            LatLng ll = new LatLng(lat,lng);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
            googleMap.animateCamera(update);
            firsttime = 0;
        }

        final String uname = name;
        curlat = lat;
        curlong = lng;
        LatLng position = new LatLng(curlat, curlong);

        markerOptionsmylocaiton = new MarkerOptions().position(position).title(uname).anchor(0.5f, 1f);
        markermylocation = googleMap.addMarker(markerOptionsmylocaiton);

        LatLng latlang = new LatLng(curlat, curlong);
        animateMarker(markermylocation, latlang, false);
    }

    public void animateMarker(final Marker marker, final LatLng toPosition,
                              final boolean hideMarker)
    {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = googleMap.getProjection();
        Point startPoint = proj.toScreenLocation(marker.getPosition());
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final long duration = 500;

        final Interpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable()
        {
            @Override
            public void run()
            {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * toPosition.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * toPosition.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));

                if (t < 1.0)
                {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                }
                else
                {
                    if (hideMarker)
                    {
                        marker.setVisible(false);
                    }
                    else
                    {
                        marker.setVisible(true);
                    }
                }
            }
        });
    }
}

