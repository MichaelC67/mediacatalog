package com.example.mediacatalog.service;

import com.example.mediacatalog.model.*;
import com.example.mediacatalog.repository.*;
import com.example.mediacatalog.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MediaService {

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private CDRepository cdRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private JeuVideoRepository jeuVideoRepository;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    // Méthodes pour les livres
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    public Livre getLivreById(Long id) {
        return livreRepository.findById(id).orElse(null);
    }

    public Livre saveLivre(Livre livre, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            fileUploadUtil.saveFile(fileName, file);
            livre.setIllustrationPath("/uploads/" + fileName);
        }
        return livreRepository.save(livre);
    }

    public Livre updateLivre(Livre livre, MultipartFile file) throws IOException {
        // Conserver l'image existante si aucun nouveau fichier n'est uploadé
        if (file == null || file.isEmpty()) {
            Livre existant = livreRepository.findById(livre.getId()).orElse(null);
            if (existant != null) {
                livre.setIllustrationPath(existant.getIllustrationPath());
            }
        } else {
            // Sinon, sauvegarder le nouveau fichier
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            fileUploadUtil.saveFile(fileName, file);
            livre.setIllustrationPath("/uploads/" + fileName);
        }
        return livreRepository.save(livre);
    }

    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }

    // Méthodes pour les CDs
    public List<CD> getAllCDs() {
        return cdRepository.findAll();
    }

    public CD getCDById(Long id) {
        return cdRepository.findById(id).orElse(null);
    }

    public CD saveCD(CD cd, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            fileUploadUtil.saveFile(fileName, file);
            cd.setIllustrationPath("/uploads/" + fileName);
        }
        return cdRepository.save(cd);
    }

    public CD updateCD(CD cd, MultipartFile file) throws IOException {
        // Conserver l'image existante si aucun nouveau fichier n'est uploadé
        if (file == null || file.isEmpty()) {
            CD existant = cdRepository.findById(cd.getId()).orElse(null);
            if (existant != null) {
                cd.setIllustrationPath(existant.getIllustrationPath());
            }
        } else {
            // Sinon, sauvegarder le nouveau fichier
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            fileUploadUtil.saveFile(fileName, file);
            cd.setIllustrationPath("/uploads/" + fileName);
        }
        return cdRepository.save(cd);
    }

    public void deleteCD(Long id) {
        cdRepository.deleteById(id);
    }

    // Méthodes pour les films
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public Film getFilmById(Long id) {
        return filmRepository.findById(id).orElse(null);
    }

    public Film saveFilm(Film film, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            fileUploadUtil.saveFile(fileName, file);
            film.setIllustrationPath("/uploads/" + fileName);
        }
        return filmRepository.save(film);
    }

    public Film updateFilm(Film film, MultipartFile file) throws IOException {
        // Conserver l'image existante si aucun nouveau fichier n'est uploadé
        if (file == null || file.isEmpty()) {
            Film existant = filmRepository.findById(film.getId()).orElse(null);
            if (existant != null) {
                film.setIllustrationPath(existant.getIllustrationPath());
            }
        } else {
            // Sinon, sauvegarder le nouveau fichier
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            fileUploadUtil.saveFile(fileName, file);
            film.setIllustrationPath("/uploads/" + fileName);
        }
        return filmRepository.save(film);
    }

    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }

    // Méthodes pour les jeux vidéo
    public List<JeuVideo> getAllJeuxVideo() {
        return jeuVideoRepository.findAll();
    }

    public JeuVideo getJeuVideoById(Long id) {
        return jeuVideoRepository.findById(id).orElse(null);
    }

    public JeuVideo saveJeuVideo(JeuVideo jeuVideo, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            fileUploadUtil.saveFile(fileName, file);
            jeuVideo.setIllustrationPath("/uploads/" + fileName);
        }
        return jeuVideoRepository.save(jeuVideo);
    }

    public JeuVideo updateJeuVideo(JeuVideo jeuVideo, MultipartFile file) throws IOException {
        // Conserver l'image existante si aucun nouveau fichier n'est uploadé
        if (file == null || file.isEmpty()) {
            JeuVideo existant = jeuVideoRepository.findById(jeuVideo.getId()).orElse(null);
            if (existant != null) {
                jeuVideo.setIllustrationPath(existant.getIllustrationPath());
            }
        } else {
            // Sinon, sauvegarder le nouveau fichier
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            fileUploadUtil.saveFile(fileName, file);
            jeuVideo.setIllustrationPath("/uploads/" + fileName);
        }
        return jeuVideoRepository.save(jeuVideo);
    }

    public void deleteJeuVideo(Long id) {
        jeuVideoRepository.deleteById(id);
    }
}
