package com.example.myapplication.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MovieAdapter;
import com.example.myapplication.database.MovieRepository;
import com.example.myapplication.model.Movie;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

/**
 * Pantalla principal que exhibe la cartelera de cine en un RecyclerView animado.
 * Gestiona el menú superior (Acerca de, Ir al cine, Cómo llegar) y la navegación a DetailActivity.
 */
public class MainActivity extends AppCompatActivity implements MovieAdapter.OnMovieClickListener {

    // Coordenadas y nombre del cine de referencia (Centro Comercial Cine Planelles)
    public static final String CINEMA_LAT_LNG = "37.3298398,-5.4172532";
    public static final String CINEMA_NAME = "Centro Comercial Cine Planelles";

    private MaterialToolbar toolbarMain;
    private RecyclerView recyclerViewMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);

        // Ajuste dinámico de los márgenes de sistema (barra de estado y barra de navegación)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupToolbar();
        setupRecyclerView();
    }

    /**
     * Inicializa las referencias a los componentes visuales del layout.
     */
    private void initViews() {
        toolbarMain = findViewById(R.id.toolbarMain);
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
    }

    /**
     * Configura la ToolBar de Material3 como ActionBar de la pantalla.
     */
    private void setupToolbar() {
        setSupportActionBar(toolbarMain);
    }

    /**
     * Configura el RecyclerView con su layout manager, datos y animación de carga.
     */
    private void setupRecyclerView() {
        List<Movie> movieList = MovieRepository.getMovies();
        MovieAdapter movieAdapter = new MovieAdapter(this, movieList, this);

        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMovies.setAdapter(movieAdapter);

        // Ejecuta la animación definida en res/anim/layout_animation_slide_up.xml
        recyclerViewMovies.scheduleLayoutAnimation();
    }

    /**
     * Infla el menú de opciones superior.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * Gestiona las pulsaciones sobre los ítems del menú superior.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_find_cinema) {
            // Opción "Ir al cine": abre la app de Google Maps con la ubicación del cine
            openCinemaLocation();
            return true;
        } else if (id == R.id.action_how_to_get) {
            // Opción "Cómo llegar": abre la navegación paso a paso con ruta, tiempo estimado y tráfico
            openCinemaDirections();
            return true;
        } else if (id == R.id.action_about) {
            // Opción "Acerca de": abre la Activity informativa
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Abre la aplicación de mapas mostrando la ubicación exacta del cine.
     */
    private void openCinemaLocation() {
        Uri locationUri = Uri.parse("geo:" + CINEMA_LAT_LNG + "?q=" + Uri.encode(CINEMA_NAME));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, locationUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        try {
            startActivity(mapIntent);
        } catch (ActivityNotFoundException e) {
            // Fallback para cualquier otra app de mapas del dispositivo (sin abrir navegador)
            Intent fallbackIntent = new Intent(Intent.ACTION_VIEW, locationUri);
            try {
                startActivity(fallbackIntent);
            } catch (ActivityNotFoundException ex) {
                Toast.makeText(this, R.string.error_sin_app_mapas, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Abre el modo de navegación paso a paso hacia el cine en Google Maps (cálculo de ruta y tiempo).
     */
    private void openCinemaDirections() {
        Uri navigationUri = Uri.parse("google.navigation:q=" + CINEMA_LAT_LNG);
        Intent navigationIntent = new Intent(Intent.ACTION_VIEW, navigationUri);
        navigationIntent.setPackage("com.google.android.apps.maps");

        try {
            startActivity(navigationIntent);
        } catch (ActivityNotFoundException e) {
            // Fallback para cualquier otra app de navegación instalada
            Intent genericNavIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q=" + CINEMA_LAT_LNG + "(" + Uri.encode(CINEMA_NAME) + ")"));
            try {
                startActivity(genericNavIntent);
            } catch (ActivityNotFoundException ex) {
                Toast.makeText(this, R.string.error_sin_app_mapas, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Callback invocado al pulsar una película en el RecyclerView.
     * Abre DetailActivity enviando los datos del film mediante Intent.
     */
    @Override
    public void onMovieClick(Movie movie) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(detailIntent);
    }
}
