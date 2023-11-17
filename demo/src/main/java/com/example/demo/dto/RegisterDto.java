package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotEmpty(message = "username can not be empty")
    private String username;
    @NotEmpty(message = "password can not be empty")
    private String password;
    @NotEmpty(message = "email can not be empty")
    @Email
    private String email;
    @NotEmpty(message = "address can not be empty")
    private String address;
    @NotEmpty(message = "phone can not be empty")
    private String phone;
}
