package com.manasses.school.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Contact {
    @NotBlank(message = "Name must not be blank")
    @Size(min = 3,message = "Name must be at least 3 characters long")
    private String name;
    @NotBlank(message = "mobile number must not be blank")
    @Pattern(regexp = "$|[0-9]{10}",message = "mobile must be 10 digits")
    private String mobileNum;
    @NotBlank(message = "Email must not be blank")
    @Email(message = "please provide a validate email address")
    private String email;
    @NotBlank(message = "subject must not be blank")
    @Size(min = 4,message = "Subject must be at least 5 characters")
    private String subject;
    @NotBlank(message = "message must not be blank")
    @Size(min = 10,message = "Message must be at least 10 characters long")
    private String message;

}