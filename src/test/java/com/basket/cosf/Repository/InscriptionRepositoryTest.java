package com.basket.cosf.Repository;


import com.basket.cosf.Model.Inscription;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.assertj.core.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@DataJpaTest
class InscriptionRepositoryTest {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Test
    void testFindAll() {
        //then
        List<Inscription> result = inscriptionRepository.findAll();
        //when
        //assertions
        assertEquals(3, result.size(), "The list of inscriptions shouldn't be empty .");
        assertEquals("Alioune", result.get(0).getFirstName());
        assertEquals("Sow", result.get(0).getLastName(), "The first inscription should be Alioune Diop.");
    }

    @Test
    void testFindById() {
        //given
        Integer id = 2; // Assuming an inscription with ID 1 exists
        //when
        Inscription result = inscriptionRepository.findById(id).orElse(null);
        //then
        assertEquals(id, result.getId(), "The inscription ID should match the one we searched for.");
        assertEquals("Fatou", result.getFirstName(), " The first name should be Fatou.");
        assertEquals("Diop", result.getLastName(), "The last name should be Diop.");
    }

    @Test
    void testSave() {
        //create a new inscription
        Inscription inscription = Inscription.builder()
                .firstName("test")
                .lastName("test")
                .email("<EMAIL>")
                .build();

        //when
        Inscription savedInscription = inscriptionRepository.save(inscription);

        //then
        assertEquals("test", savedInscription.getFirstName(), "The first name should be 'test'.");
        assertEquals("test", savedInscription.getLastName(), "The last name should be 'test'.");
        assertEquals("<EMAIL>", savedInscription.getEmail(), "The email should be '<EMAIL>'.");
        Assertions.assertThat(savedInscription.getId()).isNotNull();
    }

    @Test
    void testDeleteById() {
        //given
        Integer id = 3; // Assuming an inscription with ID 3 exists
        //when
        inscriptionRepository.deleteById(id);
        //then
        Inscription result = inscriptionRepository.findById(id).orElse(null);
        assertEquals(null, result, "The inscription should be deleted and not found.");
    }

}