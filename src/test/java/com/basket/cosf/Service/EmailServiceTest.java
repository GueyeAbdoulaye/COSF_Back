package com.basket.cosf.Service;

import com.basket.cosf.Dto.ContactMessageDto;
import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.IExpectationSetters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(EasyMockRunner.class)
class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    private MailSender mailSenderMock;

    @BeforeEach
    void setUp() {
        mailSenderMock = EasyMock.mock(MailSender.class);
        emailService = new EmailService(mailSenderMock);
    }

    @Test
    public void testSendMailContact() {
        // Given (mets des valeurs cohérentes avec ce que ton service formate)
        ContactMessageDto message = ContactMessageDto.builder()
                .prenom("Abdoulaye")
                .nom("Gueye")
                .email("Email")
                .message("Message de test")
                .build();

        // Capture the SimpleMailMessage sent by the service
        // Utilise EasyMock.Capture pour capturer l'instance de SimpleMailMessage
        Capture<SimpleMailMessage> mailCapture = EasyMock.newCapture();

        // Expectation
        // Ici, on s'attend à ce que le mail soit envoyé avec les valeurs
        mailSenderMock.send(EasyMock.capture(mailCapture));
        EasyMock.expectLastCall().once();

        EasyMock.replay(mailSenderMock);

        // When
        emailService.sendMailContact(message);

        // Then
        EasyMock.verify(mailSenderMock);

        // Assertions utiles (adapte selon ton EmailService)
        SimpleMailMessage sent = mailCapture.getValue();
        assertNotNull(sent);
        assertArrayEquals(new String[]{"m.abdoulaye.gueye@gmail.com"}, sent.getTo()); // si c'est le destinataire défini dans mon service
        assertEquals("Email", sent.getReplyTo()); // si tu mappes bien replyTo
        assertNotNull(sent.getText());
        assertTrue(sent.getText().contains("Abdoulaye Gueye"));
        assertTrue(sent.getText().contains("Message de test"));

    }


}