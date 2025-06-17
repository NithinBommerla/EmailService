package dev.nithin.emailservice.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.nithin.emailservice.Dto.SendEmailDto;
import dev.nithin.emailservice.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
public class EmailConsumer {

    private final ObjectMapper objectMapper;

    public EmailConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "send-email-topic", groupId = "emailService")
    public void sendEmail(String message) {
        System.out.println(message);

        SendEmailDto sendEmailDto;

        try {
            sendEmailDto = objectMapper.readValue(message, SendEmailDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        final String fromEmail = sendEmailDto.getFrom();
        // This should be securely fetched from a config or environment variable
        final String password = System.getenv("GMAIL_PASSWORD");
        ; // This should be securely fetched from a config or environment variable
        final String toEmail = sendEmailDto.getTo();

        System.out.println("TLS Email Service started");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator authenticator = new Authenticator() {
            //override the getPasswordAuthentication method
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getInstance(props, authenticator);

        EmailUtil.sendEmail(session, toEmail, sendEmailDto.getSubject(), sendEmailDto.getBody());
    }
}
