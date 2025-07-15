package com.basket.cosf.Controller;

import com.basket.cosf.Model.Standing;
import com.basket.cosf.Service.StandingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/standing")
@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
public class StandingController {

    private final StandingService standingService;

    /**
     * Show all standings for a specific season.
     */
    @GetMapping("/{seasonId}")
    public List<Standing> getAllStandingsBySeasonId(@PathVariable Integer seasonId) {
        return standingService.getAllStandingsBySeasonId(seasonId);
    }

    /**
     * Get all standings.
     */
    @GetMapping("/all")
    public List<Standing> getAllStandings() {
        return standingService.getAllStandings();
    }


}
