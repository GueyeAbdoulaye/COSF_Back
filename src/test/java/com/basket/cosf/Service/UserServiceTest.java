package com.basket.cosf.Service;

import com.basket.cosf.Config.JwtUtils;
import com.basket.cosf.Dto.AuthenticationRequest;
import com.basket.cosf.Dto.AuthenticationResponse;
import com.basket.cosf.Dto.UserDto;
import com.basket.cosf.Model.Inscription;
import com.basket.cosf.Model.Role;
import com.basket.cosf.Model.User;
import com.basket.cosf.Repository.UserRepository;
import com.basket.cosf.Validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(EasyMockRunner.class)
class UserServiceTest {

    private UserService userService;

    private UserRepository userRepositoryMock;
    private ObjectsValidator<UserDto> validatorMock;
    private PasswordEncoder passwordEncoderMock;
    private JwtUtils jwtUtilsMock;
    private AuthenticationManager authenticationManagerMock;

    @BeforeEach
    void setUp() {
        userRepositoryMock = EasyMock.createMock(UserRepository.class);
        validatorMock = EasyMock.createMock(ObjectsValidator.class);
        passwordEncoderMock = EasyMock.createMock(PasswordEncoder.class);
        jwtUtilsMock = EasyMock.createMock(JwtUtils.class);
        authenticationManagerMock = EasyMock.createMock(AuthenticationManager.class);

        userService = new UserService(userRepositoryMock, validatorMock, passwordEncoderMock, jwtUtilsMock, authenticationManagerMock);
    }

    @Test
    void testSaveUser() {
        UserDto userDto = UserDto.builder()
                .username("testUser")
                .password("testPassword")
                .email("test@test.com")
                .build();

        // Mock the validation
        validatorMock.validate(userDto);
        EasyMock.expectLastCall().once();
        // Mock the password encoding
        EasyMock.expect(passwordEncoderMock.encode(userDto.getPassword())).andReturn("encodedPassword").once();

        Capture<User> entityCapture = EasyMock.newCapture();

        // Mock the user repository save method
        EasyMock.expect(userRepositoryMock.save(EasyMock.capture(entityCapture))).andReturn(
                User.builder()
                        .id(1)
                        .build()
        ).once();

        // Mock the role assignment
        EasyMock.replay(userRepositoryMock, validatorMock, passwordEncoderMock);

        //when
        Integer savedId = userService.save(userDto);

        //then
        EasyMock.verify(userRepositoryMock, validatorMock, passwordEncoderMock);

        assertEquals(1, savedId);
        User savedEntity = entityCapture.getValue();
        assertNotNull(savedEntity);
        assertEquals("test@test.com", savedEntity.getUsername());
        assertEquals("encodedPassword", savedEntity.getPassword());
        assertEquals("test@test.com", savedEntity.getEmail());
    }

    @Test
    void testRegisterUser() {
        UserDto userDto = UserDto.builder()
                .username("newUser")
                .password("newPassword")
                .email("test@test.com")
                .build();

        // Mock the validation
        validatorMock.validate(userDto);
        EasyMock.expectLastCall().once();

        // Mock the password encoding
        EasyMock.expect(passwordEncoderMock.encode(userDto.getPassword())).andReturn("encodedNewPassword").once();

        Capture<User> entityCapture = EasyMock.newCapture();

        // Mock the user repository save method
        EasyMock.expect(userRepositoryMock.save(EasyMock.capture(entityCapture))).andReturn(
                User.builder()
                        .id(1)
                        .build()
        ).once();

        // Mock the JWT token generation
        EasyMock.expect(jwtUtilsMock.generateToken(EasyMock.anyObject(User.class))).andReturn("jwtToken").once();

        EasyMock.replay(userRepositoryMock, validatorMock, passwordEncoderMock, jwtUtilsMock);

        // When
        AuthenticationResponse responseResult = userService.register(userDto);

        // Then
        EasyMock.verify(userRepositoryMock, validatorMock, passwordEncoderMock, jwtUtilsMock);
        assertNotNull(responseResult);
        assertEquals("jwtToken", responseResult.getToken());
        User savedEntity = entityCapture.getValue();
        assertNotNull(savedEntity);
        assertEquals("test@test.com", savedEntity.getUsername());
        assertEquals("encodedNewPassword", savedEntity.getPassword());
        assertEquals("test@test.com", savedEntity.getEmail());
        assertEquals(Role.USER, savedEntity.getRole());
    }

    @Test
    void testAuthenticateUser() {
        AuthenticationRequest request = AuthenticationRequest.builder()
                .email("test@test.com")
                .password("testPassword")
                .build();

        Authentication authResult =
                new UsernamePasswordAuthenticationToken(request.getEmail(), null, Collections.emptyList());
        EasyMock.expect(authenticationManagerMock.authenticate(EasyMock.isA(Authentication.class)))
                .andReturn(authResult)
                .once();

        // 2) userRepository.findByEmail(...) => Optional<UserDetails>
        User userEntity = User.builder()
                .email(request.getEmail())
                .password("encodedPassword")
                .build(); // assure-toi que User implémente UserDetails
        EasyMock.expect(userRepositoryMock.findByEmail(request.getEmail()))
                .andReturn(Optional.of(userEntity))
                .once();

        // Mock the JWT token generation
        EasyMock.expect(jwtUtilsMock.generateToken(EasyMock.anyObject(User.class))).andReturn("jwtToken").once();

        EasyMock.replay(userRepositoryMock, authenticationManagerMock, jwtUtilsMock);

        // When
        AuthenticationResponse responseResult = userService.authenticate(request);
        // Then
        EasyMock.verify(userRepositoryMock, authenticationManagerMock, jwtUtilsMock);
        assertNotNull(responseResult);
        assertEquals("jwtToken", responseResult.getToken());
    }

    @Test
    void testAuthenticateUser_UserNotFound() {
        // Given
        AuthenticationRequest request = AuthenticationRequest.builder()
                .email(null)
                .password(null)
                .build();

        Authentication authResult =
                new UsernamePasswordAuthenticationToken(request.getEmail(), null, Collections.emptyList());
        EasyMock.expect(authenticationManagerMock.authenticate(EasyMock.isA(Authentication.class)))
                .andReturn(authResult)
                .once();

        // Mock the user repository to return an empty Optional
        EasyMock.expect(userRepositoryMock.findByEmail(request.getEmail()))
                .andReturn(null)
                .once();

        // Mock the JWT token generation
        AuthenticationResponse responseResult = userService.authenticate(request);


    }


    @Test
    void testFindAllUsers() {
        // Given
        List<User> userList = Arrays.asList(
                User.builder()
                        .id(1)
                        .username("user1")
                        .email("test")
                        .password("password1")
                        .build(),
                User.builder()
                        .id(2)
                        .username("user2")
                        .email("test2")
                        .password("password2")
                        .build()
        );

        EasyMock.expect(userRepositoryMock.findAll()).andReturn(userList);

        EasyMock.replay(userRepositoryMock);

        // When
        List<UserDto> result = userService.findAll();

        // Then
        EasyMock.verify(userRepositoryMock);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("test", result.get(0).getUsername());
        assertEquals("test2", result.get(1).getUsername());
    }

    @Test
    void testFindById_UserFound() {
        // Given
        int userId = 1;
        User userEntity = User.builder()
                .id(userId)
                .username("John")
                .email("john@example.com")
                .build();

        EasyMock.expect(userRepositoryMock.findById(userId))
                .andReturn(Optional.of(userEntity))
                .once();

        EasyMock.replay(userRepositoryMock);

        // When
        UserDto result = userService.findById(userId);

        // Then
        EasyMock.verify(userRepositoryMock);
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    void testFindById_UserNotFound() {
        // Given
        int userId = 999;
        EasyMock.expect(userRepositoryMock.findById(userId))
                .andReturn(Optional.empty())
                .once();

        EasyMock.replay(userRepositoryMock);

        // When / Then
        assertThrows(EntityNotFoundException.class, () -> userService.findById(userId));

        EasyMock.verify(userRepositoryMock);
    }

    @Test
    void testDeleteUser() {
        // Given
        int userId = 1;

        // Mock the user repository delete method
        userRepositoryMock.deleteById(userId);
        EasyMock.expectLastCall().once();

        EasyMock.replay(userRepositoryMock);

        // When
        userService.delete(userId);

        // Then
        EasyMock.verify(userRepositoryMock);
    }



}

