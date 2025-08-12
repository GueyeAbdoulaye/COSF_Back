package com.basket.cosf.Repository;

import com.basket.cosf.Model.Role;
import com.basket.cosf.Model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    void testFindByEmail() {


        User user = User.builder()
                .username("john_doe")
                .lastname("Doe")
                .password("password123")
                .email("EMAIL")
                .role(Role.ADMIN)
                .build();

        userRepository.save(user);

        // When
        var foundUser = userRepository.findByEmail(user.getEmail());

        // Then
        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());

        Assertions.assertThat(foundUser)
                .contains(user);

    }

    @Test
    void testFindById() {
        // Given
        int id = 1; // Assuming there is a user with ID 1 in the database

        User user = userRepository.findById(id).orElse(null);

        Assertions.assertThat(user)
                .isNotNull()
                .hasFieldOrPropertyWithValue("lastname", "Gueye")
                .hasFieldOrPropertyWithValue("email", "gueye@example.com");
    }

}