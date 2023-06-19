package com.inventoryms.ims.controllers;

import java.util.List;

import com.inventoryms.ims.models.UserEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryms.ims.models.UserEntity;
import com.inventoryms.ims.repository.UserRepository;

@RestController
class UserController {

  private final UserRepository repository;

  UserController(UserRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/users")
  List<UserEntity> all() {
    return repository.findAll();
  }

  // create user route
  @PostMapping("/users")
  UserEntity newUser(@RequestBody UserEntity newUser) {
    return repository.save(newUser);
  }

  @GetMapping("/users/{id}")
  UserEntity one(@PathVariable Long id) {

    return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found"));
  }

  // update user infor
  @PutMapping("/users/{id}")
  UserEntity updateUser(@RequestBody UserEntity newUser, @PathVariable Long id) {

    return repository.findById(id)
        .map(userEntity -> {
          userEntity.setName(newUser.getName());
          userEntity.setRole(newUser.getRole());
          return repository.save(userEntity);
        })
        .orElseGet(() -> {
          newUser.setId(id);
          return repository.save(newUser);
        });
  }

  // remove user by ID
  @DeleteMapping("/users/{id}")
  void deleteUser(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
