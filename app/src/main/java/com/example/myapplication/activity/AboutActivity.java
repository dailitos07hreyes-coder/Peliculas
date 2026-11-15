package com.example.myapplication.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.google.android.material.appbar.MaterialToolbar;

/**
 * Activity "Acerca de" que presenta información detallada sobre la aplicación,
 * el desarrollador, el curso académico y las tecnologías empleadas.
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_about);

        // Control de márgenes de sistema (Edge-to-Edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.aboutMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupToolbar();
    }

    /**
     * Configura la ToolBar con el título y el botón de retroceso.
     */
    private void setupToolbar() {
        MaterialToolbar toolbarAbout = findViewById(R.id.toolbarAbout);
        setSupportActionBar(toolbarAbout);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbarAbout.setNavigationOnClickListener(v -> finish());
    }
}
