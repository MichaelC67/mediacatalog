package com.example.mediacatalog.model;

import jakarta.persistence.Entity;

@Entity
public class Film extends Media {
    private String realisateur;
    private String format;

    public Film() {
    }

    public Film(String titre, int annee, String genre, String realisateur, String format, String illustrationPath) {
        super(titre, annee, genre, illustrationPath);
        this.realisateur = realisateur;
        this.format = format;
    }

    // Getters et Setters
    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
