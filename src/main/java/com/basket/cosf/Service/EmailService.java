package com.basket.cosf.Service;

import com.basket.cosf.Dto.ContactMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private MailSender  sentMail;

    public void sendMailContact(ContactMessageDto message){

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("m.abdoulaye.gueye@gmail.com");
        mail.setReplyTo(message.email);
        mail.setText(
                "De : " + message.prenom + " " + message.nom + "\n" +
                        "Email : " + message.email + "\n\n" +
                        "Message :\n" + message.message
        );
        sentMail.send(mail);

    }
}
