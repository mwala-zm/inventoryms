package com.inventoryms.ims.services;

import com.inventoryms.ims.dto.RegistrationDto;
import com.inventoryms.ims.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
