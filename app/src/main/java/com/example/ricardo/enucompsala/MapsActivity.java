package com.example.ricardo.enucompsala;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMapClickListener,OnMapReadyCallback {

    // Atributos importantes para a classe
    private double latitude,longitude;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // recuperando valores passados pela activity anterior.
        Intent intent = getIntent();
        nome = intent.getStringExtra("nome");
        latitude = Double.parseDouble(intent.getStringExtra("latitude"));
        longitude = Double.parseDouble(intent.getStringExtra("longitude"));
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
    /**
     * Manipula o mapa assim que ele estiver disponivel.
     * Este callback eh invocado quando o mapa estiver pronto para ser usado.
     * Aqui eh onde podemos colocar marcadores, linhas, adicionar listeners ou movimentar a camera.
     * Nesse caso, nos apenas inserimos um marcador na localizaçao passada por Intent da classe
     * MainActivity.
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Aqui criamos o gerenciador de Localizaçao e um atributo do tipo GoogleMap.
        LocationManager locationManager = null;
        GoogleMap mMap;

        try {
            // Aqui convertemos a localizaçao do sistema para o tipo LocationManager.
            locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            // Criamos uma variavel do tipo Criteria (criterio)
            Criteria criteria = new Criteria();
            // Recebemos o melhor provedor a partir do comando abaixo
            String provider = locationManager.getBestProvider(criteria, true);
            Toast.makeText(getApplicationContext(), "Provider: " + provider, Toast.LENGTH_LONG).show();

            // Nossa variavel mMap recebe o googleMap.
            mMap = googleMap;
            // Adiciona um listener para quando o mapa for clicado pelo usuario executar alguma açao
            mMap.setOnMapClickListener(MapsActivity.this);
            // Ajustamos o zoom
            mMap.getUiSettings().setZoomControlsEnabled(true);
            // Deixa a localizacao do celular habilitada
            mMap.setMyLocationEnabled(true);
        } catch (SecurityException ex) {

            Log.e("TAG", "Error", ex); // Em caso de erro
        }
        mMap = googleMap;
        final Location laslocation;
        // Verifica-se a permisao para Android 6.0 ou superior.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // Recebe a ultima localizaçao conhecida pelo aparelho
        laslocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Toast.makeText(getApplicationContext(),"Lati:"+laslocation.getLatitude()+"\nLong:: "+laslocation.getLongitude(),Toast.LENGTH_SHORT).show();

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-2.9082825, -41.7541362);


        //mMap.addMarker(new MarkerOptions().position(sydney).title("Casa Stark"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //aqui é setada a localizacao do ponto atravês das variaveis obtidas na tela anterior
        LatLng sydney = new LatLng(latitude,longitude);

        //confiuracoes do marcador(icone) no mapa, a posicao, title e etc.
        MarkerOptions marker = new MarkerOptions();
        marker.position(sydney);
        marker.title(nome);
        mMap.addMarker(marker);

        // mMap.addMarker(new MarkerOptions().position(new LatLng(lista.get(i).getLatitude(),lista.get(i).getLongitude())).title(lista.get(i).getNome()));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Toast.makeText(getApplicationContext(),"Latitude:"+latLng.latitude+"\nLongitude:"+latLng.longitude,Toast.LENGTH_SHORT).show();
    }
}
