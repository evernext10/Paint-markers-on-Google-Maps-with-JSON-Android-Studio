package com.moviles.jrsdevelopers.mapafinal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private Marker marcador;
    private EditText etBuscar;
    double lat = 0.0;
    double lng = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        etBuscar = (EditText)findViewById(R.id.etBuscar);
        Button buscar = (Button) findViewById(R.id.btBuscar);
        buscar.setOnClickListener(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        miUbicacion();
    }

    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 13);
        if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions().position(coordenadas).title("Mi posicion Actual"));
        mMap.animateCamera(miUbicacion);
    }

    private void actualizarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    private void miUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,0,locListener);
    }

    private void BuscarRestaurantes(){
        InputStream inputStream = getResources().openRawResource(R.raw.restaurantes);
        Scanner scanner = new Scanner(inputStream);
        String str = "";

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            str = str + line;
        }
        Log.v("myapp",str);

        //Enviar el String al JSON

        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray coordenadasArray = jsonObject.getJSONArray("coordinates");


            for(int i=0; i<coordenadasArray.length();i++){

                JSONArray segundoArray = coordenadasArray.getJSONArray(i);

                double lat = segundoArray.getDouble(1);
                double lng = segundoArray.getDouble(0);

                Log.v("myapp","lat = "+lat+", long = "+lng);
                //marcador.remove();
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(lat,lng));
                mMap.addMarker(markerOptions);
            }

        } catch (JSONException e) {
            Log.v("myapp",e.getMessage());
        }
    }

    private void BuscarBares(){
        InputStream inputStream = getResources().openRawResource(R.raw.bares);
        Scanner scanner = new Scanner(inputStream);
        String str = "";

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            str = str + line;
        }
        Log.v("myapp",str);

        //Enviar el String al JSON

        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray coordenadasArray = jsonObject.getJSONArray("coordinates");


            for(int i=0; i<coordenadasArray.length();i++){

                JSONArray segundoArray = coordenadasArray.getJSONArray(i);

                double lat = segundoArray.getDouble(1);
                double lng = segundoArray.getDouble(0);

                Log.v("myapp","lat = "+lat+", long = "+lng);
                //marcador.remove();
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(lat,lng));
                mMap.addMarker(markerOptions);
            }

        } catch (JSONException e) {
            Log.v("myapp",e.getMessage());
        }
    }

    private void BuscarTransmilenio(){
        InputStream inputStream = getResources().openRawResource(R.raw.transmilenio);
        Scanner scanner = new Scanner(inputStream);
        String str = "";

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            str = str + line;
        }
        Log.v("myapp",str);

        //Enviar el String al JSON

        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray coordenadasArray = jsonObject.getJSONArray("coordinates");


            for(int i=0; i<coordenadasArray.length();i++){

                JSONArray segundoArray = coordenadasArray.getJSONArray(i);

                double lat = segundoArray.getDouble(1);
                double lng = segundoArray.getDouble(0);

                Log.v("myapp","lat = "+lat+", long = "+lng);
                //marcador.remove();
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(lat,lng));
                mMap.addMarker(markerOptions);
            }

        } catch (JSONException e) {
            Log.v("myapp",e.getMessage());
        }
    }

    private void BuscarUniversidades(){
        InputStream inputStream = getResources().openRawResource(R.raw.universidades);
        Scanner scanner = new Scanner(inputStream);
        String str = "";

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            str = str + line;
        }
        Log.v("myapp",str);

        //Enviar el String al JSON

        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray coordenadasArray = jsonObject.getJSONArray("coordinates");


            for(int i=0; i<coordenadasArray.length();i++){

                JSONArray segundoArray = coordenadasArray.getJSONArray(i);

                double lat = segundoArray.getDouble(1);
                double lng = segundoArray.getDouble(0);

                Log.v("myapp","lat = "+lat+", long = "+lng);
               // marcador.remove();
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(lat,lng));
                mMap.addMarker(markerOptions);
            }

        } catch (JSONException e) {
            Log.v("myapp",e.getMessage());
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btBuscar:
                if(etBuscar.getText().toString().equals("restaurantes") || etBuscar.getText().toString().equals("Restaurantes")
                        || etBuscar.getText().toString().equals("restaurante") || etBuscar.getText().toString().equals("Restaurante")){

                    mMap.clear();
                    BuscarRestaurantes();
                }
                else if(etBuscar.getText().toString().equals("bares") || etBuscar.getText().toString().equals("Bares")
                        || etBuscar.getText().toString().equals("bar") || etBuscar.getText().toString().equals("Bar")){

                    mMap.clear();
                    BuscarBares();
                }
                else if(etBuscar.getText().toString().equals("Transmilenio") || etBuscar.getText().toString().equals("transmilenio")){
                    mMap.clear();
                    BuscarTransmilenio();
                }
                else if(etBuscar.getText().toString().equals("universidades") || etBuscar.getText().toString().equals("Universidades")
                        || etBuscar.getText().toString().equals("universidad") || etBuscar.getText().toString().equals("Universidad")){

                    mMap.clear();
                    BuscarUniversidades();
                }
                break;

        }

    }
}
