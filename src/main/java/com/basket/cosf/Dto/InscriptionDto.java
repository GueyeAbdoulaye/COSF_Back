package com.basket.cosf.Dto;

import com.basket.cosf.Model.Inscription;
import com.basket.cosf.Model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class InscriptionDto {

    private Integer id;

    @NonNull
    @NotEmpty(message = "firstname cannot be empty")
    @NotBlank(message = "firstname cannot be blank")
    private String firstName;

    @NonNull
    @NotEmpty(message = "lastname cannot be empty")
    @NotBlank(message = "lastname cannot be blank")
    private String lastName;

    @NonNull
    private String sex;

    @NonNull
    private LocalDate birthDate;

    @NonNull
    @NotEmpty(message = "phone cannot be empty")
    @NotBlank(message = "phone cannot be blank")
    private String phone;

    @NonNull
    @NotEmpty(message = "address cannot be empty")
    @NotBlank(message = "address cannot be blank")
    private String address;

    @NonNull
    @NotEmpty(message = "postalCode cannot be empty")
    @NotBlank(message = "postalCode cannot be blank")
    private String postalCode;

    @NonNull
    @NotEmpty(message = "city cannot be empty")
    @NotBlank(message = "city cannot be blank")
    private String city;

    @NonNull
    @NotEmpty(message = "country cannot be empty")
    @NotBlank(message = "country cannot be blank")
    private String country;

    @NonNull
    @NotEmpty(message = "email cannot be empty")
    @NotBlank(message = "email cannot be blank")
    private String email;

    @NonNull
    @NotEmpty(message = "mutated cannot be empty")
    @NotBlank(message = "mutated cannot be blank")
    private String mutated;

    public static InscriptionDto fromEntity(Inscription inscription){
        //null check
        return InscriptionDto.builder()
                .id(inscription.getId())
                .firstName(inscription.getFirstName())
                .lastName(inscription.getLastName())
                .email(inscription.getEmail())
                .sex(inscription.getSex())
                .birthDate(inscription.getBirthDate())
                .phone(inscription.getPhone())
                .address(inscription.getAddress())
                .postalCode(inscription.getPostalCode())
                .city(inscription.getCity())
                .country(inscription.getCountry())
                .mutated(inscription.getMutated())
                .build();
    }

    public static Inscription toEntity(InscriptionDto dto) {
        //null check
        return Inscription.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .sex(dto.getSex())
                .birthDate(dto.getBirthDate())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .postalCode(dto.getPostalCode())
                .city(dto.getCity())
                .country(dto.getCountry())
                .mutated(dto.getMutated())
                .build();
    }
}
