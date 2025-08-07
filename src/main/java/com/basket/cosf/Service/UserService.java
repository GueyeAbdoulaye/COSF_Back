package com.basket.cosf.Service;

import com.basket.cosf.Config.JwtUtils;
import com.basket.cosf.Dto.AuthenticationRequest;
import com.basket.cosf.Dto.AuthenticationResponse;
import com.basket.cosf.Dto.UserDto;
import com.basket.cosf.Model.Role;
import com.basket.cosf.Model.User;
import com.basket.cosf.Validators.ObjectsValidator;

import com.basket.cosf.Repository.UserRepository;
import com.basket.cosf.Service.Interface.AbstractService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements AbstractService<UserDto> {

    private final UserRepository userRepository;
    private final ObjectsValidator<UserDto> validator;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;


    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userRepository.save(user).getId();
    }

    @Override
    public List<UserDto> findAll() {

        return userRepository.findAll()
                .stream().
                map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    public void delete(Integer id) {


    }

    public AuthenticationResponse register(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.USER );
        var savedUser = userRepository.save(user);

        jwtUtils.generateToken(savedUser);
        return AuthenticationResponse.builder()
                .token(jwtUtils.generateToken(savedUser))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        final UserDetails user = userRepository.findByEmail(request.getEmail()).get();

        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + request.getEmail());
        }
        final String jwt = jwtUtils.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

}
