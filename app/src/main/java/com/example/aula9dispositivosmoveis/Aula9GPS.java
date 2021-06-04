package com.example.aula9dispositivosmoveis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Aula9GPS extends AppCompatActivity implements LocationListener {

    private TextView txtLat, txtLong, txtStatus;
    LocationManager locationManager;
    ListView listLocation;
    List<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula9_gps);

        txtLat = findViewById(R.id.textLatitude);
        txtLong = findViewById(R.id.textLongitude);
        txtStatus = findViewById(R.id.textStatus);
        listLocation = findViewById(R.id.listLocation);
        lista = new ArrayList<>();

        long tempo = 0;
        float distancia = 10;

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Por favor, permita o uso da localização nas configurações", Toast.LENGTH_LONG).show();
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, tempo, distancia, this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, tempo, distancia, this);
    }


    @Override
    public void onLocationChanged(Location location) {
        txtLat.setText("Latitude = " + location.getLatitude());
        txtLong.setText("Longitude = " + location.getLongitude());
        txtStatus.setText("Fornecedor = " + location.getProvider());

        String str = "Latitude = " + location.getLatitude() + "  Longitude = " + location.getLongitude();
        lista.add(str);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        listLocation.setAdapter(adapter);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}