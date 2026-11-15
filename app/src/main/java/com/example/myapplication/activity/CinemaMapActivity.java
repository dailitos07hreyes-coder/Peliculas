package com.example.myapplication.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

/**
 * Activity que integra el SDK de Google Maps para mostrar la ubicación del cine
 * y lanzar la navegación "Cómo llegar" en Google Maps.
 */
public class CinemaMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final String EXTRA_SHOW_ROUTE = "extra_show_route";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;

    // Coordenadas del cine (Destino)
    public static final double CINEMA_LAT = 37.3298398;
    public static final double CINEMA_LNG = -5.4172532;
    public static final LatLng CINEMA_LOCATION = new LatLng(CINEMA_LAT, CINEMA_LNG);
    public static final float DEFAULT_ZOOM = 16.5f;

    private MaterialToolbar toolbarMap;
    private MaterialButton btnCenterMap;
    private MaterialButton btnDirections;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Si se abrió directamente desde la opción "Cómo llegar" del menú, lanza Google Maps
        if (getIntent() != null && getIntent().getBooleanExtra(EXTRA_SHOW_ROUTE, false)) {
            abrirComoLlegarGoogleMaps();
            finish();
            return;
        }

        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_cinema_map);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mapRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupToolbar();
        setupMapFragment();
        setupButtons();
    }

    private void initViews() {
        toolbarMap = findViewById(R.id.toolbarMap);
        btnCenterMap = findViewById(R.id.btnCenterMap);
        btnDirections = findViewById(R.id.btnDirections);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbarMap);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbarMap.setNavigationOnClickListener(v -> finish());
    }

    private void setupMapFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    private void setupButtons() {
        // Reenfocar la cámara sobre el cine en el mapa de la API
        btnCenterMap.setOnClickListener(v -> {
            if (googleMap != null) {
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(CINEMA_LOCATION, DEFAULT_ZOOM));
            }
        });

        // Abrir "Cómo llegar" en Google Maps hacia las coordenadas del cine
        btnDirections.setOnClickListener(v -> abrirComoLlegarGoogleMaps());
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        this.googleMap = map;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(false);

        enableUserLocationIfPermitted();

        // Marcador con el nombre y subtítulo del cine
        Marker cinemaMarker = googleMap.addMarker(new MarkerOptions()
                .position(CINEMA_LOCATION)
                .title(getString(R.string.nombre_cine))
                .snippet(getString(R.string.subtitulo_cine))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        if (cinemaMarker != null) {
            cinemaMarker.showInfoWindow();
        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CINEMA_LOCATION, DEFAULT_ZOOM));
    }

    /**
     * Lanza la pantalla oficial de "Cómo llegar" de Google Maps hacia el cine.
     */
    private void abrirComoLlegarGoogleMaps() {
        Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination="
                + CINEMA_LAT + "," + CINEMA_LNG + "&travelmode=driving");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            // Respaldo en navegador si el emulador no tiene instalada la app de Google Maps
            startActivity(new Intent(Intent.ACTION_VIEW, gmmIntentUri));
        }
    }

    private void enableUserLocationIfPermitted() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (googleMap != null) {
                try {
                    googleMap.setMyLocationEnabled(true);
                } catch (SecurityException ignored) {
                }
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && googleMap != null) {
            try {
                googleMap.setMyLocationEnabled(true);
            } catch (SecurityException ignored) {
            }
        }
    }
}