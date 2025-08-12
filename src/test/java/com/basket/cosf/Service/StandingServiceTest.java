package com.basket.cosf.Service;

import com.basket.cosf.Model.Standing;
import com.basket.cosf.Repository.StandingRepository;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(EasyMockRunner.class)
class StandingServiceTest {

    private StandingService standingService;
    private StandingRepository standingRepositoryMock;

    @BeforeEach
    void setUp() {
        standingRepositoryMock = EasyMock.createMock(StandingRepository.class);
        standingService = new StandingService(standingRepositoryMock);
    }

    @Test
    void testGetAllStandingsBySeasonId() {
        Integer seasonId = 1;
        List<Standing> expectedStandings = Arrays.asList(
                Standing.builder()
                        .points(10)
                        .played(5)
                        .build(),
                Standing.builder()
                        .points(9)
                        .played(5)
                        .build()
        );


        EasyMock.expect(standingRepositoryMock.findAllBySeasonId(seasonId)).andReturn(expectedStandings);
        EasyMock.replay(standingRepositoryMock);

        List<Standing> actualStandings = standingService.getAllStandingsBySeasonId(seasonId);

        assertNotNull(actualStandings);
        assertEquals(expectedStandings.size(), actualStandings.size());
        assertEquals(expectedStandings.get(0).getPoints(), actualStandings.get(0).getPoints());
        assertEquals(expectedStandings.get(1).getPoints(), actualStandings.get(1).getPoints());

        EasyMock.verify(standingRepositoryMock);
    }

    @Test
    void testGetAllStandings() {
        List<Standing> expectedStandings = Arrays.asList(
                Standing.builder()
                        .points(10)
                        .played(5)
                        .build(),
                Standing.builder()
                        .points(9)
                        .played(5)
                        .build()
        );

        EasyMock.expect(standingRepositoryMock.findAll()).andReturn(expectedStandings);
        EasyMock.replay(standingRepositoryMock);

        List<Standing> actualStandings = standingService.getAllStandings();

        assertNotNull(actualStandings);
        assertEquals(expectedStandings.size(), actualStandings.size());
        assertEquals(expectedStandings.get(0).getPoints(), actualStandings.get(0).getPoints());
        assertEquals(expectedStandings.get(1).getPoints(), actualStandings.get(1).getPoints());

        EasyMock.verify(standingRepositoryMock);
    }

}