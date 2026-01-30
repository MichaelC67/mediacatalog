package com.example.mediacatalog.model;

import jakarta.persistence.Entity;

@Entity
public class JeuVideo extends Media {
    private String editeur;
    private String plateforme;

    public JeuVideo() {
    }

    public JeuVideo(String titre, int annee, String genre, String editeur, String plateforme, String illustrationPath) {
        super(titre, annee, genre, illustrationPath);
        this.editeur = editeur;
        this.plateforme = plateforme;
    }

    // Getters et Setters
    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getPlateforme() {
        return plateforme;
    }

    public void setPlateforme(String plateforme) {
        this.plateforme = plateforme;
    }
}
