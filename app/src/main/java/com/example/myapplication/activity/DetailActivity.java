package com.example.myapplication.activity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.model.Movie;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Locale;

/**
 * Activity encargada de presentar los detalles completos de la película seleccionada.
 * Muestra el póster a gran resolución, ficha técnica (director, fecha), calificación y sinopsis extendida.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    private MaterialToolbar toolbarDetail;
    private ImageView ivDetailPoster;
    private TextView tvDetailTitle;
    private TextView tvDetailGenre;
    private RatingBar ratingBarDetail;
    private TextView tvDetailRatingNumber;
    private TextView tvDetailDirector;
    private TextView tvDetailReleaseDate;
    private TextView tvDetailFullSynopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_detail);

        // Control de márgenes de sistema (Edge-to-Edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupToolbar();
        displayMovieDetails();
    }

    /**
     * Inicializa las referencias a las vistas del layout.
     */
    private void initViews() {
        toolbarDetail = findViewById(R.id.toolbarDetail);
        ivDetailPoster = findViewById(R.id.ivDetailPoster);
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailGenre = findViewById(R.id.tvDetailGenre);
        ratingBarDetail = findViewById(R.id.ratingBarDetail);
        tvDetailRatingNumber = findViewById(R.id.tvDetailRatingNumber);
        tvDetailDirector = findViewById(R.id.tvDetailDirector);
        tvDetailReleaseDate = findViewById(R.id.tvDetailReleaseDate);
        tvDetailFullSynopsis = findViewById(R.id.tvDetailFullSynopsis);
    }

    /**
     * Configura la ToolBar con el botón de retroceso habilitado.
     */
    private void setupToolbar() {
        setSupportActionBar(toolbarDetail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // Navegación hacia atrás al pulsar el icono de flecha
        toolbarDetail.setNavigationOnClickListener(v -> finish());
    }

    /**
     * Recupera el objeto Movie enviado desde MainActivity y rellena los campos en pantalla.
     */
    private void displayMovieDetails() {
        Movie movie;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            movie = getIntent().getSerializableExtra(EXTRA_MOVIE, Movie.class);
        } else {
            movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        }

        if (movie != null) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(movie.getTitulo());
            }

            if (movie.getImageResId() != 0) {
                ivDetailPoster.setImageResource(movie.getImageResId());
            }

            tvDetailTitle.setText(movie.getTitulo());
            tvDetailGenre.setText(movie.getGenero());
            ratingBarDetail.setRating(movie.getRating());
            tvDetailRatingNumber.setText(String.format(Locale.getDefault(), "%.1f / 5.0", movie.getRating()));
            tvDetailDirector.setText(movie.getDirector());
            tvDetailReleaseDate.setText(movie.getFechaDeLanzamiento());
            tvDetailFullSynopsis.setText(movie.getSipnosis());
        }
    }
}
