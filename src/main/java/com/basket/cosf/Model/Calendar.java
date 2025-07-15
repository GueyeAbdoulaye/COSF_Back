package com.basket.cosf.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "calendar", schema = "COSF")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String journeys;

    @Column(name = "await_team_id")
    private Integer awaitTeamId;

    private Date matchDate;

    private String location;

    private String status;

    @Column(name = "score_home")
    private Integer scoreHome;

    @Column(name = "score_away")
    private Integer scoreAway;

   @Column(name = "season_id")
    private Integer seasonId;
}
