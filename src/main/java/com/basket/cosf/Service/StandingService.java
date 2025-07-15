package com.basket.cosf.Service;

import com.basket.cosf.Model.Standing;
import com.basket.cosf.Repository.StandingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StandingService {

    private final StandingRepository standingRepository;
    /**
     * show all standings for a specific season.
     */
    public List<Standing> getAllStandingsBySeasonId(Integer seasonId) {
        return standingRepository.findAllBySeasonId(seasonId);
    }

    /**
     * get all standings.
     */
    public List<Standing> getAllStandings() {
        return standingRepository.findAll();
    }
}
