package edu.sjsu.fall2016.cs175.maps;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMapLoadedCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapFragment mf = (MapFragment) getFragmentManager().findFragmentById(R.id.mymap);
        mf.getMapAsync(this);

    }

    @Override
    public void onMapLoaded() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 0, 0, // provider, min time/distance
                new LocationListener() {
                    public void onLocationChanged(Location location) { // code to run when user's location changes
                        Toast.makeText(MainActivity.this, "Location changed to " + location.toString(), Toast.LENGTH_LONG).show();
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
                        showNotification(new LatLng(location.getLatitude(), location.getLongitude()));
                    }

                    public void onStatusChanged(String prov, int stat, Bundle b) {
                    }

                    public void onProviderEnabled(String provider) {
                    }

                    public void onProviderDisabled(String provider) {
                    }
                });
    }

    private void showNotification(LatLng location) {
        Notification.Builder builder = new Notification.Builder(this).setContentTitle("Location Update")
                .setContentText("Location changed to " + location.toString()).setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 12, intent, 0);
        builder.setContentIntent(pi);
        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) getSystemService(MainActivity.NOTIFICATION_SERVICE);
        manager.notify(701, notification);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setOnMapLoadedCallback(this);
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.setOnMapClickListener(this);

        // Needs a context for location service. Use getActivity() if using from a fragment.
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (loc == null) {
            // fall back to network if GPS is not available
            loc = locationManager.getLastKnownLocation( LocationManager.NETWORK_PROVIDER);
        }

        double myLat = 37.7749, myLng = -122.4194;
        if (loc != null) {
             myLat = loc.getLatitude();
             myLng = loc.getLongitude();
        }
        LatLng myPosition = new LatLng(myLat, myLng);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition,
                11.0f));

        Marker markPosition = googleMap.addMarker(new MarkerOptions()
                .position(myPosition)
                .title("I am here")
                .draggable(true));

    }

    @Override
    public void onMapClick(LatLng latLng) {
        Marker markPosition = mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("I clicked here"));
        showNotification(latLng);
    }
}
