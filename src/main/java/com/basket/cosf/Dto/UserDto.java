package com.basket.cosf.Dto;

import com.basket.cosf.Model.User;

import jakarta.validation.constraints.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer Id;

    @NotNull
    @NotEmpty(message = "Username cannot be empty")
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotNull
    @NotEmpty(message = "Lastname cannot be empty")
    @NotBlank(message = "Lastname cannot be blank")
    private String lastname;

    @NotNull
    @NotEmpty(message = "Email cannot be empty")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull
    @NotEmpty(message = "Password cannot be empty")
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 16)
    private String password;

    public static UserDto fromEntity(User user){
        //null check
        return UserDto.builder()
                .Id(user.getId())
                .username(user.getUsername())  // This returns email due to UserDetails override
                .lastname(user.getLastname())  // Lombok generated getter
                .email(user.getEmail())        // Lombok generated getter  
                .password(user.getPassword())  // UserDetails override, returns password
                .build();
    }

    public static User toEntity(UserDto userDto) {
        //null check
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
