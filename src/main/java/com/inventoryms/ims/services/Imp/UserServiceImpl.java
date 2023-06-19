package com.inventoryms.ims.services.Imp;

import com.inventoryms.ims.dto.RegistrationDto;
import com.inventoryms.ims.models.Role;
import com.inventoryms.ims.models.UserEntity;
import com.inventoryms.ims.repository.RoleRepository;
import com.inventoryms.ims.repository.UserRepository;
import com.inventoryms.ims.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public void saveUser(RegistrationDto registrationDto){
        UserEntity user = new UserEntity();
        user.setName(registrationDto.getName());
        user.setName(registrationDto.getUsername());
        user.setName(registrationDto.getEmail());
        user.setName(registrationDto.getPassword());
        Role role = roleRepository.findByName("USER");
        user.setRole(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByEmail(username);
    }


}
