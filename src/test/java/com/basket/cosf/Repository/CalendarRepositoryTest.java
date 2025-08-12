package com.basket.cosf.Repository;

import com.basket.cosf.Model.Calendar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.assertj.core.api.Assertions;

import org.springframework.data.domain.Pageable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CalendarRepositoryTest {

    @Autowired
    private CalendarRepository calendarRepository;

    @Test
    void testFindCalendarById() {
        // Given
        Integer id = 1; // Assuming a season with ID 1 exists

        // When
        Calendar result = calendarRepository.findById(id).orElse(null);

        // Then
        Assertions.assertThat(result).isNotNull();
        assertEquals(id, result.getId(), "The calendar ID should match the one we searched for.");
        assertEquals("Stade Léopold Sédar Senghor", result.getLocation());
    }

    @Test
    void testFindAll(){

        // When
        List<Calendar> result = calendarRepository.findAll();

        // Then
        assertEquals(4, result.size(), "The list of calendars shouldn't be empty.");
        assertEquals("Stade Léopold Sédar Senghor", result.get(0).getLocation(), "The first calendar should be at Stade Léopold Sédar Senghor.");
    }

    @Test
    void testSave() {
        // Given
        Calendar calendar = Calendar.builder()
                .location("test location")
                .scoreAway(125)
                .scoreHome(100)
                .seasonId(1)
                .build();

        // When
        Calendar savedCalendar = calendarRepository.save(calendar);

        // Then
        assertEquals("test location", savedCalendar.getLocation(), "The location should be 'test location'.");
        assertEquals(1, savedCalendar.getSeasonId(), "The season ID should be 1.");
        Assertions.assertThat(savedCalendar.getId()).isNotNull();
    }

    @Test
    void testDeleteById() {
        // Given
        Integer id = 2; // Assuming a calendar with ID 2 exists

        // When
        calendarRepository.deleteById(id);

        // Then
        Calendar result = calendarRepository.findById(id).orElse(null);
        Assertions.assertThat(result).isNull();
    }

    @Test
    void testFindAllBySeasonId() {
        // Given
        Integer seasonId = 1; // Assuming a season with ID 1 exists

        // When
        List<Calendar> result = calendarRepository.findAllBySeasonId(seasonId);

        // Then
        assertEquals(4, result.size(), "There should be 4 calendars for season ID 1.");
        assertEquals("Stade Léopold Sédar Senghor", result.get(0).getLocation(), "The first calendar should be at Stade Léopold Sédar Senghor.");
    }

    @Test
    void testFindAllByOrderByMatchDateDesc() {
        // When
        List<Calendar> result = calendarRepository.findAllByOrderByMatchDateDesc();

        // Then
        assertEquals(4, result.size(), "There should be 4 calendars in descending order of match date.");
        assertEquals("2025-10-08 00:00:00.0", result.get(0).getMatchDate().toString(), "The first match date should be the most recent.");
    }

    @Test
    void testFindLastMatchPlayed() {
        // Given
        Pageable pageable = Pageable.ofSize(1);

        // When
        List<Calendar> result = calendarRepository.findAllPlayedMatch(pageable);

        // Then
        assertEquals(1, result.size(), "There should be 1 played match.");
        assertEquals("2025-07-22 00:00:00.0", result.get(0).getMatchDate().toString(), "The last played match date should be correct.");
    }

    @Test
    void testFindNextMatchComing() {
        // Given
        Pageable pageable = Pageable.ofSize(2);

        // When
        List<Calendar> result = calendarRepository.findAllMatchesComing(pageable);

        // Then
        assertEquals(2, result.size(), "There should be 2 upcoming matches.");
        assertEquals("2025-10-01 00:00:00.0", result.get(0).getMatchDate().toString(), "The first upcoming match date should be correct.");
    }


}