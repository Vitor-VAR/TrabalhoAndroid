package com.iesb.android1.aulaii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                //Aqui obtemos a localizacao atual do meu dispositivo

                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                System.out.println("Latitude: " + latitude +" - Longitude: "+ longitude);
            }
        };
    }
//    private void startLocationUpdate(){
//        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACESS_FINE_LOATION)== PackageManager.)
//    }

}