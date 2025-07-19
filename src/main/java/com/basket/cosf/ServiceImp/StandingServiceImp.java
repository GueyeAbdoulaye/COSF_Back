package com.basket.cosf.ServiceImp;

import com.basket.cosf.Dto.StandingDto;
import com.basket.cosf.Model.Calendar;
import com.basket.cosf.Service.StandingService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StandingServiceImp {

    @Autowired
    private StandingService standingService;

    public List<StandingDto> getAllStandingsBySeasonId(Integer seasonId) {
        // Convert the list of Standing to StandingDto
        return standingService.getAllStandingsBySeasonId(seasonId)
                .stream()
                .map(standing -> StandingDto.builder()
                        .teamId(standing.getTeamId())
                        .points(standing.getPoints())
                        .wins(standing.getWins())
                        .losses(standing.getLosses())
                        .draws(standing.getDraws())
                        .pointScored(standing.getPointScored())
                        .pointAgainst(standing.getPointAgainst())
                        .seasonId(seasonId)
                        .build())
                .toList();
    }

    // Additional methods or overrides can be added here if needed
}
