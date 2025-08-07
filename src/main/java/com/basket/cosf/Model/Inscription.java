package com.basket.cosf.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "inscription", schema = "COSF")
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private String sex;

    private LocalDate birthDate;

    private String phone;

    private String address;

    private String postalCode;

    private String city;

    private String country;

    private String email;

    private String mutated;

}
