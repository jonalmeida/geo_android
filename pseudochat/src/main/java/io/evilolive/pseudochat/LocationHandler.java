package io.evilolive.pseudochat;

import android.content.Context;
import android.os.Bundle;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.util.Log;

import java.util.Date;

/**
 * Created by Jonathan Almeida on 10/03/14.
 */
public class LocationHandler {
    private static final String LOCATION_HANDLER = "LocationHandler";
    private Location location;
    private LocationManager locationManager;
    private Context context;
    private Date date;

    LocationHandler(Context context) {
        this.context = context;
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        setLocation(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
        this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1.0f, locationListener);
    }

    public Location getLocation() {
        return location;
    }

    private void setLocation(Location location) {
        this.location = location;
    }

    LocationListener locationListener = new LocationListener() {
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
    };
}
