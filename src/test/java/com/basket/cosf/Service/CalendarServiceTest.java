package com.basket.cosf.Service;

import com.basket.cosf.Model.Calendar;
import com.basket.cosf.Repository.CalendarRepository;
import org.assertj.core.api.Assertions;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.easymock.EasyMock.replay;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(EasyMockRunner.class)
class CalendarServiceTest {

    @Autowired
    private CalendarService calendarService;


    private CalendarRepository calendarRepositoryMock;

    @BeforeEach
    void setUp() {
        calendarRepositoryMock = EasyMock.mock(CalendarRepository.class);
        calendarService = new CalendarService(calendarRepositoryMock);
    }

    @Test
    public void testGetAllCalendarsNotEmpty() {
        // Given
        List<Calendar> calendarList = Arrays.asList(
                Calendar.builder()
                        .scoreHome(155)
                        .scoreAway(140)
                        .location("France")
                        .build(),
                Calendar.builder()
                        .scoreHome(100)
                        .scoreAway(90)
                        .location("Spain")
                        .build(),
                Calendar.builder()
                        .scoreHome(65)
                        .scoreAway(70)
                        .location("Italy")
                        .build()
        );

        //Expect
        EasyMock.expect(calendarRepositoryMock.findAllByOrderByMatchDateDesc()).andReturn(calendarList);

        replay(calendarRepositoryMock);

        // When
        List<Calendar> result = calendarService.getAllCalendars();

        // Then
        assertNotNull(result);
        Assertions.assertThat(result)
                .hasSize(3)
                .extracting(Calendar::getLocation)
                .containsExactly("France", "Spain", "Italy");

        }

    @Test
    public void testGetAllCalendarsEmpty() {
        // Given
        List<Calendar> calendarList = Arrays.asList();

        //Expect
        EasyMock.expect(calendarRepositoryMock.findAllByOrderByMatchDateDesc()).andReturn(calendarList);

        replay(calendarRepositoryMock);

        // When
        List<Calendar> result = calendarService.getAllCalendars();

        // Then
        assertNotNull(result);
        Assertions.assertThat(result)
                .isEmpty();
        Assertions.assertThat(result)
                .hasSize(0);
    }

    @Test
    public void testGetCalendarsBySeasonIdEmpty() {
        // Given
        Integer seasonId = 1;
        List<Calendar> calendarList = Arrays.asList();

        // Expect
        EasyMock.expect(calendarRepositoryMock.findAllBySeasonId(seasonId)).andReturn(calendarList);

        EasyMock.replay(calendarRepositoryMock);

        // When
        List<Calendar> result = calendarService.getCalendarsBySeasonId(seasonId);

        // Then
        assertNotNull(result);
        Assertions.assertThat(result)
                .isEmpty();
        Assertions.assertThat(result)
                .hasSize(0);
    }

    @Test
    public void testGetCalendarsBySeasonIdNotEmpty() {
        // Given
        Integer seasonId = 1;
        List<Calendar> calendarList = Arrays.asList(
                Calendar.builder()
                        .scoreHome(155)
                        .scoreAway(140)
                        .location("France")
                        .build(),
                Calendar.builder()
                        .scoreHome(100)
                        .scoreAway(90)
                        .location("Spain")
                        .build(),
                Calendar.builder()
                        .scoreHome(65)
                        .scoreAway(70)
                        .location("Italy")
                        .build()
        );

        // Expect
        EasyMock.expect(calendarRepositoryMock.findAllBySeasonId(seasonId)).andReturn(calendarList);

        EasyMock.replay(calendarRepositoryMock);

        // When
        List<Calendar> result = calendarService.getCalendarsBySeasonId(seasonId);

        // Then
        assertNotNull(result);
        Assertions.assertThat(result)
                .hasSize(3)
                .extracting(Calendar::getLocation)
                .containsExactly("France", "Spain", "Italy");
    }

    @Test
    public void testGetLastPlayedMatch() {
    // Given
        List<Calendar> calendarList = Arrays.asList(
                Calendar.builder()
                        .scoreHome(155)
                        .scoreAway(140)
                        .location("France")
                        .build(),
                Calendar.builder()
                        .scoreHome(100)
                        .scoreAway(90)
                        .location("Spain")
                        .build(),
                Calendar.builder()
                        .scoreHome(65)
                        .scoreAway(70)
                        .location("Italy")
                        .build()
        );

        // Expect
        EasyMock.expect(calendarRepositoryMock.findAllPlayedMatch(EasyMock.anyObject())).andReturn(calendarList);

        EasyMock.replay(calendarRepositoryMock);

        // When
        var result = calendarService.getLastPlayedMatch();

        // Then
        assertTrue(result.isPresent());
        assertEquals("France", result.get().getLocation());
    }

    @Test
    public void testGetAllPlayedMatch() {
        // Given
        List<Calendar> calendarList = Arrays.asList(
                Calendar.builder()
                        .scoreHome(155)
                        .scoreAway(140)
                        .location("France")
                        .build(),
                Calendar.builder()
                        .scoreHome(100)
                        .scoreAway(90)
                        .location("Spain")
                        .build(),
                Calendar.builder()
                        .scoreHome(65)
                        .scoreAway(70)
                        .location("Italy")
                        .build()
        );

        // Expect
        EasyMock.expect(calendarRepositoryMock.findAllPlayedMatch(EasyMock.anyObject())).andReturn(calendarList);

        EasyMock.replay(calendarRepositoryMock);

        // When
        List<Calendar> result = calendarService.getAllPlayedMatch();

        // Then
        assertNotNull(result);
        Assertions.assertThat(result)
                .hasSize(3)
                .extracting(Calendar::getLocation)
                .containsExactly("France", "Spain", "Italy");
    }

    @Test
    public void testGetNextPlayedMatch() {
    // Given
        List<Calendar> calendarList = Arrays.asList(
                Calendar.builder()
                        .scoreHome(155)
                        .scoreAway(140)
                        .location("France")
                        .build(),
                Calendar.builder()
                        .scoreHome(100)
                        .scoreAway(90)
                        .location("Spain")
                        .build(),
                Calendar.builder()
                        .scoreHome(65)
                        .scoreAway(70)
                        .location("Italy")
                        .build()
        );

        // Expect
        EasyMock.expect(calendarRepositoryMock.findAllMatchesComing(EasyMock.anyObject())).andReturn(calendarList);

        EasyMock.replay(calendarRepositoryMock);

        // When
        var result = calendarService.getnextPlayedMatch();

        // Then
        assertTrue(result.isPresent());
        assertEquals("France", result.get().getLocation());
    }

    @Test
    public void testGetAllNextPlayedMatch() {
        // Given
        List<Calendar> calendarList = Arrays.asList(
                Calendar.builder()
                        .scoreHome(155)
                        .scoreAway(140)
                        .location("France")
                        .build(),
                Calendar.builder()
                        .scoreHome(100)
                        .scoreAway(90)
                        .location("Spain")
                        .build(),
                Calendar.builder()
                        .scoreHome(65)
                        .scoreAway(70)
                        .location("Italy")
                        .build()
        );

        // Expect
        EasyMock.expect(calendarRepositoryMock.findAllMatchesComing(EasyMock.anyObject())).andReturn(calendarList);

        EasyMock.replay(calendarRepositoryMock);

        // When
        List<Calendar> result = calendarService.getAllnextPlayedMatch();

        // Then
        assertNotNull(result);
        Assertions.assertThat(result)
                .hasSize(3)
                .extracting(Calendar::getLocation)
                .containsExactly("France", "Spain", "Italy");
    }

}