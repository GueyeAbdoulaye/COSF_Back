package com.basket.cosf.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente le classement des équipes dans une saison de basket.
 */

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "standing", schema = "COSF")
public class Standing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "team_id")
    private int teamId;

    private int points;

    private int played;

    private int wins;

    private int losses;

    private int draws;

    private int penalties;

    private int forfeits;

    private int defaulted;

    @Column(name = "point_scored")
    private int pointScored;

    @Column(name = "point_against")
    private int pointAgainst;

    @Column(name = "point_difference")
    private int pointDifference;

    @Column(name = "season_id")
    private int seasonId;

}
