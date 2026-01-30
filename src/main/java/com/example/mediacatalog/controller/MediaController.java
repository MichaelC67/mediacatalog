package com.example.mediacatalog.controller;

import com.example.mediacatalog.model.*;
import com.example.mediacatalog.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    // Afficher la liste des livres
    @GetMapping("/livres")
    public String getAllLivres(Model model) {
        model.addAttribute("livres", mediaService.getAllLivres());
        return "livres";
    }

    // Afficher le formulaire pour ajouter un livre
    @GetMapping("/livres/add")
    public String showAddLivreForm(Model model) {
        model.addAttribute("livre", new Livre());
        return "add-livre";
    }

    // Enregistrer un livre
    @PostMapping("/livres/save")
    public String saveLivre(@ModelAttribute Livre livre, @RequestParam(value = "file", required = false) MultipartFile file, Model model) {
        Set<String> allowed = new HashSet<>(Arrays.asList("image/jpeg", "image/png", "image/gif", "image/webp"));
        long maxBytes = 2L * 1024L * 1024L; // 2MB
        if (file != null && !file.isEmpty()) {
            if (file.getSize() > maxBytes) {
                model.addAttribute("error", "Le fichier est trop volumineux (max 2MB).");
                model.addAttribute("livre", livre);
                return "add-livre";
            }
            if (file.getContentType() == null || !allowed.contains(file.getContentType())) {
                model.addAttribute("error", "Type de fichier non autorisé. Utilisez JPG, PNG, GIF ou WEBP.");
                model.addAttribute("livre", livre);
                return "add-livre";
            }
        }
        try {
            mediaService.saveLivre(livre, file);
        } catch (IOException e) {
            model.addAttribute("error", "Erreur lors de l'enregistrement du fichier.");
            model.addAttribute("livre", livre);
            return "add-livre";
        }
        return "redirect:/media/livres";
    }

    // Éditer un livre
    @GetMapping("/livres/edit/{id}")
    public String showEditLivreForm(@PathVariable Long id, Model model) {
        Livre livre = mediaService.getLivreById(id);
        if (livre != null) {
            model.addAttribute("livre", livre);
            return "edit-livre";
        }
        return "redirect:/media/livres";
    }

    // Enregistrer les modifications d'un livre
    @PostMapping("/livres/update")
    public String updateLivre(@ModelAttribute Livre livre, @RequestParam(value = "file", required = false) MultipartFile file, Model model) {
        Set<String> allowed = new HashSet<>(Arrays.asList("image/jpeg", "image/png", "image/gif", "image/webp"));
        long maxBytes = 2L * 1024L * 1024L; // 2MB
        if (file != null && !file.isEmpty()) {
            if (file.getSize() > maxBytes) {
                model.addAttribute("error", "Le fichier est trop volumineux (max 2MB).");
                model.addAttribute("livre", livre);
                return "edit-livre";
            }
            if (file.getContentType() == null || !allowed.contains(file.getContentType())) {
                model.addAttribute("error", "Type de fichier non autorisé. Utilisez JPG, PNG, GIF ou WEBP.");
                model.addAttribute("livre", livre);
                return "edit-livre";
            }
        }
        try {
            mediaService.updateLivre(livre, file);
        } catch (IOException e) {
            model.addAttribute("error", "Erreur lors de l'enregistrement du fichier.");
            model.addAttribute("livre", livre);
            return "edit-livre";
        }
        return "redirect:/media/livres";
    }

    // Supprimer un livre
    @GetMapping("/livres/delete/{id}")
    public String deleteLivre(@PathVariable Long id) {
        mediaService.deleteLivre(id);
        return "redirect:/media/livres";
    }

    // Afficher la liste des CDs
    @GetMapping("/cds")
    public String getAllCDs(Model model) {
        model.addAttribute("cds", mediaService.getAllCDs());
        return "cds";
    }

    // Afficher le formulaire pour ajouter un CD
    @GetMapping("/cds/add")
    public String showAddCDForm(Model model) {
        model.addAttribute("cd", new CD());
        return "add-cd";
    }

    // Enregistrer un CD
    @PostMapping("/cds/save")
    public String saveCD(@ModelAttribute CD cd, @RequestParam(value = "file", required = false) MultipartFile file, Model model) {
        Set<String> allowed = new HashSet<>(Arrays.asList("image/jpeg", "image/png", "image/gif", "image/webp"));
        long maxBytes = 2L * 1024L * 1024L; // 2MB
        if (file != null && !file.isEmpty()) {
            if (file.getSize() > maxBytes) {
                model.addAttribute("error", "Le fichier est trop volumineux (max 2MB).");
                model.addAttribute("cd", cd);
                return "add-cd";
            }
            if (file.getContentType() == null || !allowed.contains(file.getContentType())) {
                model.addAttribute("error", "Type de fichier non autorisé. Utilisez JPG, PNG, GIF ou WEBP.");
                model.addAttribute("cd", cd);
                return "add-cd";
            }
        }
        try {
            mediaService.saveCD(cd, file);
        } catch (IOException e) {
            model.addAttribute("error", "Erreur lors de l'enregistrement du fichier.");
            model.addAttribute("cd", cd);
            return "add-cd";
        }
        return "redirect:/media/cds";
    }

    // Éditer un CD
    @GetMapping("/cds/edit/{id}")
    public String showEditCDForm(@PathVariable Long id, Model model) {
        CD cd = mediaService.getCDById(id);
        if (cd != null) {
            model.addAttribute("cd", cd);
            return "edit-cd";
        }
        return "redirect:/media/cds";
    }

    // Enregistrer les modifications d'un CD
    @PostMapping("/cds/update")
    public String updateCD(@ModelAttribute CD cd, @RequestParam(value = "file", required = false) MultipartFile file, Model model) {
        Set<String> allowed = new HashSet<>(Arrays.asList("image/jpeg", "image/png", "image/gif", "image/webp"));
        long maxBytes = 2L * 1024L * 1024L; // 2MB
        if (file != null && !file.isEmpty()) {
            if (file.getSize() > maxBytes) {
                model.addAttribute("error", "Le fichier est trop volumineux (max 2MB).");
                model.addAttribute("cd", cd);
                return "edit-cd";
            }
            if (file.getContentType() == null || !allowed.contains(file.getContentType())) {
                model.addAttribute("error", "Type de fichier non autorisé. Utilisez JPG, PNG, GIF ou WEBP.");
                model.addAttribute("cd", cd);
                return "edit-cd";
            }
        }
        try {
            mediaService.updateCD(cd, file);
        } catch (IOException e) {
            model.addAttribute("error", "Erreur lors de l'enregistrement du fichier.");
            model.addAttribute("cd", cd);
            return "edit-cd";
        }
        return "redirect:/media/cds";
    }

    // Supprimer un CD
    @GetMapping("/cds/delete/{id}")
    public String deleteCD(@PathVariable Long id) {
        mediaService.deleteCD(id);
        return "redirect:/media/cds";
    }

    // Afficher la liste des films
    @GetMapping("/films")
    public String getAllFilms(Model model) {
        model.addAttribute("films", mediaService.getAllFilms());
        return "films";
    }

    // Afficher le formulaire pour ajouter un film
    @GetMapping("/films/add")
    public String showAddFilmForm(Model model) {
        model.addAttribute("film", new Film());
        return "add-film";
    }

    // Enregistrer un film
    @PostMapping("/films/save")
    public String saveFilm(@ModelAttribute Film film, @RequestParam(value = "file", required = false) MultipartFile file, Model model) {
        Set<String> allowed = new HashSet<>(Arrays.asList("image/jpeg", "image/png", "image/gif", "image/webp"));
        long maxBytes = 2L * 1024L * 1024L; // 2MB
        if (file != null && !file.isEmpty()) {
            if (file.getSize() > maxBytes) {
                model.addAttribute("error", "Le fichier est trop volumineux (max 2MB).");
                model.addAttribute("film", film);
                return "add-film";
            }
            if (file.getContentType() == null || !allowed.contains(file.getContentType())) {
                model.addAttribute("error", "Type de fichier non autorisé. Utilisez JPG, PNG, GIF ou WEBP.");
                model.addAttribute("film", film);
                return "add-film";
            }
        }
        try {
            mediaService.saveFilm(film, file);
        } catch (IOException e) {
            model.addAttribute("error", "Erreur lors de l'enregistrement du fichier.");
            model.addAttribute("film", film);
            return "add-film";
        }
        return "redirect:/media/films";
    }

    // Éditer un film
    @GetMapping("/films/edit/{id}")
    public String showEditFilmForm(@PathVariable Long id, Model model) {
        Film film = mediaService.getFilmById(id);
        if (film != null) {
            model.addAttribute("film", film);
            return "edit-film";
        }
        return "redirect:/media/films";
    }

    // Enregistrer les modifications d'un film
    @PostMapping("/films/update")
    public String updateFilm(@ModelAttribute Film film, @RequestParam(value = "file", required = false) MultipartFile file, Model model) {
        Set<String> allowed = new HashSet<>(Arrays.asList("image/jpeg", "image/png", "image/gif", "image/webp"));
        long maxBytes = 2L * 1024L * 1024L; // 2MB
        if (file != null && !file.isEmpty()) {
            if (file.getSize() > maxBytes) {
                model.addAttribute("error", "Le fichier est trop volumineux (max 2MB).");
                model.addAttribute("film", film);
                return "edit-film";
            }
            if (file.getContentType() == null || !allowed.contains(file.getContentType())) {
                model.addAttribute("error", "Type de fichier non autorisé. Utilisez JPG, PNG, GIF ou WEBP.");
                model.addAttribute("film", film);
                return "edit-film";
            }
        }
        try {
            mediaService.updateFilm(film, file);
        } catch (IOException e) {
            model.addAttribute("error", "Erreur lors de l'enregistrement du fichier.");
            model.addAttribute("film", film);
            return "edit-film";
        }
        return "redirect:/media/films";
    }

    // Supprimer un film
    @GetMapping("/films/delete/{id}")
    public String deleteFilm(@PathVariable Long id) {
        mediaService.deleteFilm(id);
        return "redirect:/media/films";
    }

    // Afficher la liste des jeux vidéo
    @GetMapping("/jeuxvideo")
    public String getAllJeuxVideo(Model model) {
        model.addAttribute("jeuxvideo", mediaService.getAllJeuxVideo());
        return "jeuxvideo";
    }

    // Afficher le formulaire pour ajouter un jeu vidéo
    @GetMapping("/jeuxvideo/add")
    public String showAddJeuVideoForm(Model model) {
        model.addAttribute("jeuvideo", new JeuVideo());
        return "add-jeuvideo";
    }

    // Enregistrer un jeu vidéo
    @PostMapping("/jeuxvideo/save")
    public String saveJeuVideo(@ModelAttribute JeuVideo jeuVideo, @RequestParam(value = "file", required = false) MultipartFile file, Model model) {
        Set<String> allowed = new HashSet<>(Arrays.asList("image/jpeg", "image/png", "image/gif", "image/webp"));
        long maxBytes = 2L * 1024L * 1024L; // 2MB
        if (file != null && !file.isEmpty()) {
            if (file.getSize() > maxBytes) {
                model.addAttribute("error", "Le fichier est trop volumineux (max 2MB).");
                model.addAttribute("jeuvideo", jeuVideo);
                return "add-jeuvideo";
            }
            if (file.getContentType() == null || !allowed.contains(file.getContentType())) {
                model.addAttribute("error", "Type de fichier non autorisé. Utilisez JPG, PNG, GIF ou WEBP.");
                model.addAttribute("jeuvideo", jeuVideo);
                return "add-jeuvideo";
            }
        }
        try {
            mediaService.saveJeuVideo(jeuVideo, file);
        } catch (IOException e) {
            model.addAttribute("error", "Erreur lors de l'enregistrement du fichier.");
            model.addAttribute("jeuvideo", jeuVideo);
            return "add-jeuvideo";
        }
        return "redirect:/media/jeuxvideo";
    }

    // Éditer un jeu vidéo
    @GetMapping("/jeuxvideo/edit/{id}")
    public String showEditJeuVideoForm(@PathVariable Long id, Model model) {
        JeuVideo jeuVideo = mediaService.getJeuVideoById(id);
        if (jeuVideo != null) {
            model.addAttribute("jeuvideo", jeuVideo);
            return "edit-jeuvideo";
        }
        return "redirect:/media/jeuxvideo";
    }

    // Enregistrer les modifications d'un jeu vidéo
    @PostMapping("/jeuxvideo/update")
    public String updateJeuVideo(@ModelAttribute JeuVideo jeuVideo, @RequestParam(value = "file", required = false) MultipartFile file, Model model) {
        Set<String> allowed = new HashSet<>(Arrays.asList("image/jpeg", "image/png", "image/gif", "image/webp"));
        long maxBytes = 2L * 1024L * 1024L; // 2MB
        if (file != null && !file.isEmpty()) {
            if (file.getSize() > maxBytes) {
                model.addAttribute("error", "Le fichier est trop volumineux (max 2MB).");
                model.addAttribute("jeuvideo", jeuVideo);
                return "edit-jeuvideo";
            }
            if (file.getContentType() == null || !allowed.contains(file.getContentType())) {
                model.addAttribute("error", "Type de fichier non autorisé. Utilisez JPG, PNG, GIF ou WEBP.");
                model.addAttribute("jeuvideo", jeuVideo);
                return "edit-jeuvideo";
            }
        }
        try {
            mediaService.updateJeuVideo(jeuVideo, file);
        } catch (IOException e) {
            model.addAttribute("error", "Erreur lors de l'enregistrement du fichier.");
            model.addAttribute("jeuvideo", jeuVideo);
            return "edit-jeuvideo";
        }
        return "redirect:/media/jeuxvideo";
    }

    // Supprimer un jeu vidéo
    @GetMapping("/jeuxvideo/delete/{id}")
    public String deleteJeuVideo(@PathVariable Long id) {
        mediaService.deleteJeuVideo(id);
        return "redirect:/media/jeuxvideo";
    }

    // Afficher la page d'accueil
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("livresCount", mediaService.getAllLivres().size());
        model.addAttribute("cdsCount", mediaService.getAllCDs().size());
        model.addAttribute("filmsCount", mediaService.getAllFilms().size());
        model.addAttribute("jeuxVideoCount", mediaService.getAllJeuxVideo().size());
        return "index";
    }
}
