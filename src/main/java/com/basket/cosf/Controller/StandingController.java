package com.basket.cosf.Controller;

import com.basket.cosf.Dto.StandingDto;
import com.basket.cosf.Model.Standing;
import com.basket.cosf.Service.StandingService;
import com.basket.cosf.ServiceImp.StandingServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/standing")
@RequiredArgsConstructor
@RestController
public class StandingController {

    private final StandingService standingService;

    private final StandingServiceImp standingServiceImp;

    /**
     * Show all standings for a specific season.
     */
    @GetMapping("/{seasonId}")
    public List<StandingDto> getAllStandingsBySeasonId(@PathVariable Integer seasonId) {
        return standingServiceImp.getAllStandingsBySeasonId(seasonId);
    }

    /**
     * Get all standings.
     */
    @GetMapping("/all")
    public List<Standing> getAllStandings() {
        return standingService.getAllStandings();
    }


}
