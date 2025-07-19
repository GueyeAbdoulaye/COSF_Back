package com.basket.cosf.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StandingDto {

    private int teamId;

    private int points;

    private int wins;

    private int losses;

    private int draws;

    private int pointScored;

    private int pointAgainst;

    private int seasonId;
}
