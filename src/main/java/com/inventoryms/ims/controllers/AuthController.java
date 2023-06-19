package com.inventoryms.ims.controllers;

import com.inventoryms.ims.dto.RegistrationDto;
import com.inventoryms.ims.models.UserEntity;
import com.inventoryms.ims.services.UserService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class AuthController {
    public UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/register")
    public String getRegisterForm(Model model){
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("register/save")
    public String register(@ModelAttribute("user")RegistrationDto user,
                            BindingResult result,Model model){
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()){
            result.rejectValue("email", "User with this email/username already exists");
        }

        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
        if(existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()){
            result.rejectValue("email", "User with this email/username already exists");
        }

        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "redirect";
        }
        userService.saveUser(user);
        return"redirect/login";
    }


}
