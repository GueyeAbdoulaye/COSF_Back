package com.basket.cosf.Controller;

import com.basket.cosf.Model.Teams;
import com.basket.cosf.Repository.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/teams")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TeamsController {

    private final TeamsRepository teamsRepository;

    /**
     * show all teams.
     */
    @GetMapping("/all")
    public List<String> getAllTeams() {
        return teamsRepository.findAll()
                .stream()
                .map(team -> team.getName())
                .toList();
    }

    /**
     * show a team by id.
     */
    @GetMapping("/{id}")
    public Teams getTeamById(@PathVariable int id) {
        return teamsRepository.findById(id);
    }
}
