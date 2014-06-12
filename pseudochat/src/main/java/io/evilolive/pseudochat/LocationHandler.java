package io.evilolive.pseudochat;

import android.content.Context;
import android.os.Bundle;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Looper;
import android.util.Log;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Jonathan Almeida on 10/03/14.
 */
public class LocationHandler implements LocationListener {
    private static final String LOCATION_HANDLER = "LocationHandler";
    private Location location;
    private LocationManager locationManager;
    private Context context;

    LocationHandler(Context context) {
        this.context = context;
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        setLocation(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
        this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1.0f, this);
    }

    public Location getLocation() {
        return location;
    }

    private void setLocation(Location location) {
        this.location = location;
    }

    public void removeUpdates() {
        this.locationManager.removeUpdates(this);
    }

    public void resumeUpdates() {
        this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1.0f, this);
    }

    public void sampleLocation() {
        this.locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, Looper.getMainLooper());
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.v(LOCATION_HANDLER, "Location changed:\n lat: " + location.getLatitude() + "\n lon: " + location.getLongitude());
        setLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.v(LOCATION_HANDLER, "Status Changed");
        if(i == LocationProvider.OUT_OF_SERVICE || i == LocationProvider.TEMPORARILY_UNAVAILABLE)
            setLocation(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
        // Get updated location
        // else // LocationProvider.AVAILABLE


        // TODO: Handle this case in the UI
        // This method is called when a provider is unable to
        // fetch a location or if the provider has recently
        // become available after a period of unavailability.
    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

}

class LocationUpdater extends TimerTask {

    private LocationHandler locationHandler;

    LocationUpdater(LocationHandler locationHandler) {
        this.locationHandler = locationHandler;
    }

    @Override
    public void run() {
        this.locationHandler.sampleLocation();
    }

}