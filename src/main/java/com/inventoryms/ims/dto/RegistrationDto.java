package com.inventoryms.ims.dto;
import lombok.Data;

@Data
public class RegistrationDto {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
}
