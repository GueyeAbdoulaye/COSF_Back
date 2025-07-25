package com.basket.cosf.Controller;

import com.basket.cosf.Dto.ContactMessageDto;
import com.basket.cosf.Service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contact")
@CrossOrigin("*")
public class ContactController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<String> recevoirMessage(@RequestBody ContactMessageDto message) {

        emailService.sendMailContact(
                message
        );
        // Tu peux stocker, envoyer un email, etc.
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
