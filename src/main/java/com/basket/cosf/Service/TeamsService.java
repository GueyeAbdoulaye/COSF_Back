package com.basket.cosf.Service;

import com.basket.cosf.Model.Teams;
import com.basket.cosf.Repository.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamsService {

    private final TeamsRepository teamsRepository;

    /**
     * Retourne toute les équipes.
     * @return une liste de toutes les équipes
     */
    public List<Teams> getAllTeams() {
        return teamsRepository.findAll();
    }

    /**
     * Retourne une équipe par son identifiant.
     * @param id l'identifiant de l'équipe
     * @return l'équipe correspondante, ou null si aucune équipe n'est trouvée
     */
    public Teams getTeamById(int id) {
        return teamsRepository.findById(id);
    }
}
