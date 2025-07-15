package com.basket.cosf.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represente une equipe de basket.
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teams", schema = "COSF")
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
