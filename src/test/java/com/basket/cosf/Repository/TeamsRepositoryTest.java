package com.basket.cosf.Repository;

import com.basket.cosf.Model.Teams;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TeamsRepositoryTest {

    @Autowired
    private TeamsRepository teamsRepository;

    @Test
    void testFindAll() {
        List<Teams> teamsResult = teamsRepository.findAll();
        assertFalse(teamsResult.isEmpty(), "The list of teams should not be empty");

        // Then
        Assertions.assertThat(teamsResult)
                .isNotNull()
                .hasSize(4)
                .extracting("name") // extrait uniquement l'attribut "name"
                .containsExactlyInAnyOrder("Team A", "Team B", "Team C", "Team D");
    }


    @Test
    void testFindById() {
        int teamId = 1; // Assuming there is a team with ID 1 in the database
        Teams teamResult = teamsRepository.findById(teamId);
        assertNotNull(teamResult, "The team should not be null");
        assertEquals(teamId, teamResult.getId(), "The team ID should match the requested ID");
        assertEquals(teamResult.getName(),"Team A");
    }

    @Test
    void testFindByIdNotFound() {
        int nonExistentTeamId = 999; // Assuming this ID does not exist in the database
        Teams teamResult = teamsRepository.findById(nonExistentTeamId);
        assertNull(teamResult, "The team should be null for a non-existent ID");
    }


}