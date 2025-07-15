package com.basket.cosf.Controller;

import com.basket.cosf.Repository.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
