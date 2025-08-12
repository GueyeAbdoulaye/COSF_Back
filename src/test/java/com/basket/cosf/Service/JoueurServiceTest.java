package com.basket.cosf.Service;

import com.basket.cosf.Model.Joueur;
import com.basket.cosf.Repository.JoueurRepository;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(EasyMockRunner.class)
class JoueurServiceTest {

    private JoueurService joueurService;
    private JoueurRepository joueurRepositoryMock;

    @BeforeEach
    void setUp() {
        joueurRepositoryMock = EasyMock.createMock(JoueurRepository.class);
        joueurService = new JoueurService(joueurRepositoryMock);
    }

    @Test
    void testGetAllJoueurs() {
        // Arrange
        Joueur joueur1 = Joueur.builder()
                .nom("John")
                .prenom("John")
                .poste("Guard")
                .numero(10)
                .build();

        Joueur joueur2 = Joueur.builder()
                .nom("Jane")
                .prenom("Jane")
                .poste("Forward")
                .numero(20)
                .build();

        EasyMock.expect(joueurRepositoryMock.findAll()).andReturn(List.of(joueur1, joueur2));
        EasyMock.replay(joueurRepositoryMock);

        // Act
        List<Joueur> joueurs = joueurService.getAllJoueurs();

        // Assert
        assertNotNull(joueurs);
        assertEquals(2, joueurs.size());
        assertEquals("John", joueurs.get(0).getNom());
        assertEquals("Jane", joueurs.get(1).getNom());

        EasyMock.verify(joueurRepositoryMock);
    }


}