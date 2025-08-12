package com.basket.cosf.Repository;

import com.basket.cosf.Model.Season;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SeasonRepositoryTest {

    @Autowired
    private SeasonRepository seasonRepository;

    // Test to find a season by ID
    @Test
    void testFindById() {
        // Given
        Integer id = 1; // Assuming a season with ID 1 exists

        // When
        Season result = seasonRepository.findById(id).orElse(null);

        // Then
        assertNotNull(result, "The season should not be null.");
        assertEquals(id, result.getId(), "The season ID should match the one we searched for.");
    }

    // Test to find all seasons
    @Test
    void testFindAll() {
        // When
        List<Season> result = seasonRepository.findAll();

        // Then
        assertFalse(result.isEmpty(), "The list of seasons should not be empty.");
        assertTrue(result.size() > 0, "There should be at least one season in the list.");
    }

    // Test to save a new season
    @Test
    void testSave() {
        // Given
        Season season = Season.builder()
                .name("SeasonOff")
                .build();

        // When
        Season savedSeason = seasonRepository.save(season);

        // Then
        assertNotNull(savedSeason.getId(), "The saved season should have an ID.");
        assertEquals("SeasonOff", savedSeason.getName(), "The season name should be 'SeasonOff'.");
    }
}