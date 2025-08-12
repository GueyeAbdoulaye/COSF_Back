package com.basket.cosf.Repository;

import com.basket.cosf.Model.Standing;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StandingRepositoryTest {

    @Autowired
    private StandingRepository standingRepository;

    @Test
    void testFindAllBySeasonId() {
        // Given
        Integer seasonId = 1; // Assuming a season with ID 1 exists

        // When
        List<Standing> standings = standingRepository.findAllBySeasonId(seasonId);

        // Then
        assertNotNull(standings, "The standings list should not be null.");
        assertFalse(standings.isEmpty(), "The standings list should not be empty.");

        Assertions.assertThat(standings)
                .hasSize(2)
                .satisfiesExactlyInAnyOrder(
                        standing -> {
                            assertEquals(1, standing.getTeamId(), "The first team's ID should be 1.");
                            assertEquals(10, standing.getPoints(), "The first team's points should be 10.");
                        },
                        standing -> {
                            assertEquals(2, standing.getTeamId(), "The second team's ID should be 2.");
                            assertEquals(8, standing.getPoints(), "The second team's points should be 8.");
                        }
                );
    }

    @Test
    void testSaveStanding() {
        // Given
        Standing standing = Standing.builder()
                .teamId(3)
                .points(5)
                .played(2)
                .wins(1)
                .losses(1)
                .draws(0)
                .penalties(0)
                .forfeits(0)
                .defaulted(0)
                .pointScored(50)
                .pointAgainst(40)
                .pointDifference(10)
                .seasonId(1) // Assuming a season with ID 1 exists
                .build();

        // When
        Standing savedStanding = standingRepository.save(standing);

        // Then
        assertNotNull(savedStanding.getId(), "The saved standing should have an ID.");
        assertEquals(3, savedStanding.getTeamId(), "The team ID should be 3.");
        assertEquals(5, savedStanding.getPoints(), "The points should be 5.");

        Assertions.assertThat(savedStanding)
                .hasFieldOrPropertyWithValue("played", 2)
                .hasFieldOrPropertyWithValue("wins", 1)
                .hasFieldOrPropertyWithValue("losses", 1)
                .hasFieldOrPropertyWithValue("draws", 0)
                .hasFieldOrPropertyWithValue("penalties", 0)
                .hasFieldOrPropertyWithValue("forfeits", 0)
                .hasFieldOrPropertyWithValue("defaulted", 0)
                .hasFieldOrPropertyWithValue("pointScored", 50)
                .hasFieldOrPropertyWithValue("pointAgainst", 40)
                .hasFieldOrPropertyWithValue("pointDifference", 10)
                .hasFieldOrPropertyWithValue("seasonId", 1);
    }

    @Test
    void testFindAllBySeasonIdEmpty() {
        // Given
        Integer seasonId = 999; // Assuming no standings exist for this season ID

        // When
        List<Standing> standings = standingRepository.findAllBySeasonId(seasonId);

        // Then
        assertNotNull(standings, "The standings list should not be null.");
        assertTrue(standings.isEmpty(), "The standings list should be empty for a non-existent season ID.");
    }


}

