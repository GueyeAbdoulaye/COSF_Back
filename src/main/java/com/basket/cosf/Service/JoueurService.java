package com.basket.cosf.Service;
import com.basket.cosf.Model.Joueur;
import com.basket.cosf.Repository.JoueurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JoueurService {

    public final JoueurRepository joueurRepository;
    public List<Joueur> getAllJoueurs() {
        return joueurRepository.findAll();
    }

}
