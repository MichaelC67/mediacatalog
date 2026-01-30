package com.example.mediacatalog.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private int annee;
    private String genre;
    private String illustrationPath;

    public Media() {
    }

    public Media(String titre, int annee, String genre, String illustrationPath) {
        this.titre = titre;
        this.annee = annee;
        this.genre = genre;
        this.illustrationPath = illustrationPath;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIllustrationPath() {
        return illustrationPath;
    }

    public void setIllustrationPath(String illustrationPath) {
        this.illustrationPath = illustrationPath;
    }
}
