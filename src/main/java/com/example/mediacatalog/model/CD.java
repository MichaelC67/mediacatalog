package com.example.mediacatalog.model;

import jakarta.persistence.Entity;

@Entity
public class CD extends Media {
    private String artiste;
    private int nombrePistes;

    public CD() {
    }

    public CD(String titre, int annee, String genre, String artiste, int nombrePistes, String illustrationPath) {
        super(titre, annee, genre, illustrationPath);
        this.artiste = artiste;
        this.nombrePistes = nombrePistes;
    }

    // Getters et Setters
    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public int getNombrePistes() {
        return nombrePistes;
    }

    public void setNombrePistes(int nombrePistes) {
        this.nombrePistes = nombrePistes;
    }
}
