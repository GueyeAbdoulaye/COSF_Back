package com.basket.cosf.Service;

import com.basket.cosf.Model.Teams;
import com.basket.cosf.Repository.TeamsRepository;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(EasyMockRunner.class)
class TeamsServiceTest {

    private TeamsService teamsService;
    private TeamsRepository teamsRepositoryMock;

    @BeforeEach
    void setUp() {
        teamsRepositoryMock = EasyMock.createMock(TeamsRepository.class);
        teamsService = new TeamsService(teamsRepositoryMock);
    }

    @Test
    void testGetAllTeams() {
        List<Teams> teamsList = Arrays.asList(
                Teams.builder().name("Team A").build(),
                Teams.builder().name("Team B").build()
        );

        EasyMock.expect(teamsRepositoryMock.findAll()).andReturn(teamsList);
        EasyMock.replay(teamsRepositoryMock);
        List<Teams> result = teamsService.getAllTeams();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Team A", result.get(0).getName());
        assertEquals("Team B", result.get(1).getName());
        EasyMock.verify(teamsRepositoryMock);
    }

    @Test
    void testGetTeamById() {
        int teamId = 1;
        Teams team = Teams.builder().id(teamId).name("Team A").build();

        EasyMock.expect(teamsRepositoryMock.findById(teamId)).andReturn(team);
        EasyMock.replay(teamsRepositoryMock);

        Teams result = teamsService.getTeamById(teamId);
        assertNotNull(result);
        assertEquals(teamId, result.getId());
        assertEquals("Team A", result.getName());

        EasyMock.verify(teamsRepositoryMock);
    }
}