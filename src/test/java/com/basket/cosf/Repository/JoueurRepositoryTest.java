package com.basket.cosf.Repository;

import com.basket.cosf.Model.Joueur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JoueurRepositoryTest {

    @Autowired
    private JoueurRepository joueurRepository;

    @Test
    void testFindById() {
        // Given
        int id = 1; // Assuming a player with ID 1 exists

        // When
        Joueur joueur = joueurRepository.findById(id).orElse(null);

        // Then
        assertNotNull(joueur, "The player should not be null.");
        assertEquals(id, joueur.getId(), "The player ID should match the one we searched for.");
    }

    @Test
    void testFindAll() {

        // When
        List<Joueur> joueurs = joueurRepository.findAll();

        // Then
        assertFalse(joueurs.isEmpty(), "The list of players should not be empty.");
        assertTrue(joueurs.size() > 4, "There should be at least 4 player in the list.");
    }

    @Test
    void testSave() {
        // Given
        Joueur joueur = Joueur.builder()
                .nom("Test")
                .prenom("Player")
                .poste("pivot")
                .build();
        // When
        Joueur savedJoueur = joueurRepository.save(joueur);

        // Then
        assertNotNull(savedJoueur.getId(), "The saved player should have an ID.");
        assertEquals("Test", savedJoueur.getNom(), "The player's name should be 'Test'.");
        assertEquals("Player", savedJoueur.getPrenom(), "The player's first name should be 'Player'.");
        assertEquals("pivot", savedJoueur.getPoste(), "The player's position should be 'pivot'.");
    }

    @Test
    void testDeleteById() {
        // Given
        int id = 1; // Assuming a player with ID 1 exists

        // When
        joueurRepository.deleteById(id);

        // Then
        assertFalse(joueurRepository.findById(id).isPresent(), "The player should be deleted.");

    }


}