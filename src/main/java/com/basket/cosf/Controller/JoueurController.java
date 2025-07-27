package com.basket.cosf.Controller;

import com.basket.cosf.Model.Joueur;
import com.basket.cosf.Service.JoueurService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/joueurs")
@CrossOrigin("*")
public class JoueurController {

    private final JoueurService joueurService;

    @GetMapping("/all")
    public List<Joueur> getAllJoueurs() {
        return joueurService.getAllJoueurs();
    }

}
