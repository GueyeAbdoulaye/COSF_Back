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
}
