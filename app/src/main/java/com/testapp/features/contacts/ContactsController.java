package com.testapp.features.contacts;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created on 19.01.2017.
 */

public class ContactsController implements OnMapReadyCallback, LocationListener {

    private static final long LOCATION_REFRESH_TIME = 5000;
    private static final long LOCATION_REFRESH_DISTANCE = 10;

    private static final LatLng[] SOME_RANDOM_MARKERS = { new LatLng(53.938777, 27.537182),
            new LatLng(53.850894, 27.510411),
            new LatLng(53.891221, 27.649955)};

    private GoogleMap mMap;

    private ContactsView contactsView;

    private Marker userMarker;
    private LatLng userLocation;

    public ContactsController(ContactsView contactsView) {
        this.contactsView = contactsView;
    }

    public void bind(Activity activity) {

        contactsView.bindView(this);
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        } else {
            try {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_REFRESH_TIME, LOCATION_REFRESH_DISTANCE, this);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME, LOCATION_REFRESH_DISTANCE, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location locationNet = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location lastLocation = getBestLocation(locationGPS, locationNet);
            LatLng userLocation = new LatLng(53.638777, 27.737182);//some default location
            if (lastLocation != null) {
                userLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            }
            this.userLocation = userLocation;
        }
    }

    public void unbind() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for (LatLng latLng : SOME_RANDOM_MARKERS) {
            mMap.addMarker(new MarkerOptions().position(latLng));
        }
        updateUserLocation(userLocation);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 8.0f));
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(getClass().getSimpleName(), "onLocationChanged: " + location);
        updateUserLocation(new LatLng(location.getLatitude(), location.getLongitude()));
    }

    private void updateUserLocation(LatLng latLng) {
        Log.d(getClass().getSimpleName(), "updateUserLocation: " + latLng);
        if (mMap != null) {
            if (userMarker != null) {
                userMarker.remove();
            }
            userLocation = latLng;
            userMarker = mMap.addMarker(new MarkerOptions().position(userLocation).title("User position"));
        }
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Nullable
    private Location getBestLocation(Location locationGPS, Location locationNet) {
        long GPSLocationTime = 0;
        if (null != locationGPS) {
            GPSLocationTime = locationGPS.getTime();
        }
        long NetLocationTime = 0;
        if (null != locationNet) {
            NetLocationTime = locationNet.getTime();
        }
        if (0 < GPSLocationTime - NetLocationTime ) {
            return locationGPS;
        } else {
            return locationNet;
        }
    }
}
