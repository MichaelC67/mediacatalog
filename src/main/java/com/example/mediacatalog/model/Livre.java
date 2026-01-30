package com.example.mediacatalog.model;

import jakarta.persistence.Entity;

@Entity
public class Livre extends Media {
    private String auteur;
    private String isbn;

    public Livre() {
    }

    public Livre(String titre, int annee, String genre, String auteur, String isbn, String illustrationPath) {
        super(titre, annee, genre, illustrationPath);
        this.auteur = auteur;
        this.isbn = isbn;
    }

    // Getters et Setters
    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
