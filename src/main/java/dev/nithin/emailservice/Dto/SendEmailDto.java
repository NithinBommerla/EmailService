package dev.nithin.emailservice.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendEmailDto {
    private String from;
    private String to;
    private String subject;
    private String body;
}
