package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Movie;

import java.util.List;
import java.util.Locale;

/**
 * Adaptador para el RecyclerView de la cartelera de películas.
 * Gestiona el inflado de la interfaz de cada ítem, la asignación de datos y la animación al entrar en pantalla.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    /**
     * Interfaz para delegar el evento de selección de película a la Activity.
     */
    public interface OnMovieClickListener {
        void onMovieClick(Movie movie);
    }

    private final Context context;
    private final List<Movie> movieList;
    private final OnMovieClickListener listener;

    // Constructor: inicializamos el contexto, la lista de películas y el listener de clics
    public MovieAdapter(Context context, List<Movie> movieList, OnMovieClickListener listener) {
        this.context = context;
        this.movieList = movieList;
        this.listener = listener;
    }

    // Establecemos qué fichero XML tiene la interfaz gráfica de cada uno de los elementos de nuestro RecyclerView
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    // Para cada uno de los objetos establece sus datos: título, género, valoración, descripción e imagen
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.tvMovieTitle.setText(movie.getTitulo());
        holder.tvMovieGenre.setText(movie.getGenero());
        holder.ratingBarMovie.setRating(movie.getRating());
        holder.tvMovieRating.setText(String.format(Locale.getDefault(), "%.1f", movie.getRating()));
        holder.tvMovieShortDesc.setText(movie.getDescripcionCorta());

        if (movie.getImageResId() != 0) {
            holder.ivMoviePoster.setImageResource(movie.getImageResId());
        }

        // Listener para abrir el detalle al pulsar sobre el elemento
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMovieClick(movie);
            }
        });
    }

    // El método getItemCount() le dice al RecyclerView cuántos elementos debe mostrar
    @Override
    public int getItemCount() {
        return movieList != null ? movieList.size() : 0;
    }

    // Método que se activa cuando la vista entra en la ventana para ejecutar la animación
    @Override
    public void onViewAttachedToWindow(@NonNull MovieViewHolder holder) {
        super.onViewAttachedToWindow(holder);

        holder.itemView.clearAnimation();

        Animation animation = AnimationUtils.loadAnimation(
                holder.itemView.getContext(),
                R.anim.item_animation_slide_up
        );

        holder.itemView.startAnimation(animation);
    }

    // Enlaza los elementos de la interfaz gráfica con las variables del adaptador
    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView ivMoviePoster;
        TextView tvMovieTitle;
        TextView tvMovieGenre;
        RatingBar ratingBarMovie;
        TextView tvMovieRating;
        TextView tvMovieShortDesc;

        // itemView representa la vista completa de un solo ítem (el layout XML inflado en onCreateViewHolder)
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMoviePoster = itemView.findViewById(R.id.ivMoviePoster);
            tvMovieTitle = itemView.findViewById(R.id.tvMovieTitle);
            tvMovieGenre = itemView.findViewById(R.id.tvMovieGenre);
            ratingBarMovie = itemView.findViewById(R.id.ratingBarMovie);
            tvMovieRating = itemView.findViewById(R.id.tvMovieRating);
            tvMovieShortDesc = itemView.findViewById(R.id.tvMovieShortDesc);
        }
    }
}
