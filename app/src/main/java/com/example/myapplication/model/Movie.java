package com.example.myapplication.model;

import java.io.Serializable;

/**
 * Modelo de datos que representa una película de la cartelera.
 * Implementa Serializable para permitir el paso de objetos entre Activities a través de Intents.
 */
public class Movie implements Serializable {

    private int id;
    private String titulo;
    private String genero;
    private float rating;
    private String descripcionCorta;
    private String sipnosis;
    private String director;
    private String fechaDeLanzamiento;
    private int imageResId;

    public Movie() {
    }

    public Movie(int id, String titulo, String genero, float rating, String descripcionCorta, String sipnosis, String director, String fechaDeLanzamiento, int imageResId) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.rating = rating;
        this.descripcionCorta = descripcionCorta;
        this.sipnosis = sipnosis;
        this.director = director;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
        this.imageResId = imageResId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public String getSipnosis() {
        return sipnosis;
    }

    public void setSipnosis(String sipnosis) {
        this.sipnosis = sipnosis;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public void setFechaDeLanzamiento(String fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
