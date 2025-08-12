package com.basket.cosf.Service;

import com.basket.cosf.Dto.InscriptionDto;
import com.basket.cosf.Model.Inscription;
import com.basket.cosf.Repository.InscriptionRepository;
import com.basket.cosf.Validators.ObjectsValidator;
import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.mail.SimpleMailMessage;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(EasyMockRunner.class)
class InscriptionServiceTest {


    private InscriptionService inscriptionService;

    private ObjectsValidator<InscriptionDto> validatorMock;
    private InscriptionRepository inscriptionRepositoryMock;

    @BeforeEach
    void setUp() {
        validatorMock = EasyMock.createMock(ObjectsValidator.class);
        inscriptionRepositoryMock = EasyMock.createMock(InscriptionRepository.class);
        inscriptionService = new InscriptionService(validatorMock, inscriptionRepositoryMock);
    }

    @Test
    void testSaveInscription() {
        InscriptionDto inscriptionDto = InscriptionDto.builder()
                .firstName("John")
                .lastName("Doe")
                .email("JonhDoe@gmail.com")
                .phone("1234567890")
                .sex("Homme")
                .birthDate(LocalDate.parse("1990-01-01"))
                .address("123 Main St")
                .city("Paris")
                .postalCode("75001")
                .country("France")
                .mutated("No")
                .build();

        // Expect the validator to validate the inscriptionDto
        validatorMock.validate(inscriptionDto);
        EasyMock.expectLastCall().once();

        Capture<Inscription> entityCapture = EasyMock.newCapture();

        // Expect the repository to save the Inscription entity
        EasyMock.expect(inscriptionRepositoryMock.save(EasyMock.capture(entityCapture)))
                .andReturn(
                        Inscription.builder()
                                .id(1)
                                .build()
                )
                .once();

        EasyMock.replay(validatorMock, inscriptionRepositoryMock);

        // When
        Integer savedId = inscriptionService.save(inscriptionDto);

        // Then
        EasyMock.verify(validatorMock, inscriptionRepositoryMock);
        assertEquals(1, savedId);
        Inscription savedEntity = entityCapture.getValue();
        assertNotNull(savedEntity);
        assertEquals("John", savedEntity.getFirstName());
        assertEquals("Doe", savedEntity.getLastName());
        assertEquals("JonhDoe@gmail.com", savedEntity.getEmail());
        assertEquals("1234567890", savedEntity.getPhone());
        assertEquals("Homme", savedEntity.getSex());
    }

    @Test
    void testFindAllInscription() {
        // Given
        List<Inscription> listInscription = Arrays.asList(

                Inscription.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .email("JonhDoe@gmail.com")
                        .phone("1234567890")
                        .sex("Homme")
                        .birthDate(LocalDate.parse("1990-01-01"))
                        .address("123 Main St")
                        .city("Paris")
                        .postalCode("75001")
                        .country("France")
                        .mutated("No")
                        .build(),
                Inscription.builder()
                        .firstName("Jane")
                        .lastName("Doe")
                        .email("JonhDoe@gmail.com")
                        .phone("0987654321")
                        .sex("Homme")
                        .birthDate(LocalDate.parse("1990-01-01"))
                        .address("123 Main St")
                        .city("Paris")
                        .postalCode("75001")
                        .country("France")
                        .mutated("No")
                        .build()
        );

        // Expect the repository to return the list of inscriptions
        EasyMock.expect(inscriptionRepositoryMock.findAll())
                .andReturn(listInscription);

        EasyMock.replay(inscriptionRepositoryMock);

        // When
        List<InscriptionDto> result = inscriptionService.findAll();
        // Then
        EasyMock.verify(inscriptionRepositoryMock);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testFindByIdInscription() {
        // Given
        Integer id = 1;
        Inscription inscription = Inscription.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .email("JonhDoe@gmail.com")
                .phone("1234567890")
                .sex("Homme")
                .birthDate(LocalDate.parse("1990-01-01"))
                .address("123 Main St")
                .city("Paris")
                .postalCode("75001")
                .country("France")
                .mutated("No")
                .build();

        // Expect the repository to find the inscription by id
        EasyMock.expect(inscriptionRepositoryMock.findById(id))
                .andReturn(Optional.of(inscription));

        EasyMock.replay(inscriptionRepositoryMock);

        // When
        InscriptionDto result = inscriptionService.findById(id);
        // Then
        EasyMock.verify(inscriptionRepositoryMock);
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }

    @Test
    void testDeleteInscription() {
        // Given
        Integer id = 1;

        // Expect the repository to delete the inscription by id
        inscriptionRepositoryMock.deleteById(id);
        EasyMock.expectLastCall().once();

        EasyMock.replay(inscriptionRepositoryMock);

        // When
        inscriptionService.delete(id);

        // Then
        EasyMock.verify(inscriptionRepositoryMock);

    }
}