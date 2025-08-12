package com.basket.cosf.Dto;

import lombok.Builder;

@Builder
public class ContactMessageDto {

    public String nom;
    public String prenom;
    public String email;
    public String message;
}
