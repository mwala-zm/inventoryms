package com.inventoryms.ims.models;

import java.util.*;

import jakarta.persistence.*;
import com.inventoryms.ims.models.Role;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> role = new HashSet<>();

  public User() {
  }

  public User(String name, Role role) {
    this.name = name;
    this.role.add(role);
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Set<Role> getRole() {
    return role;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setRole(Set<Role> roles) {
    this.role = roles;
  }

  public void addRole(Role role) {
    this.role.add(role);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof User))
      return false;
    User user = (User) o;
    return Objects.equals(id, user.id) &&
        Objects.equals(name, user.name) &&
        Objects.equals(role, user.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, role);
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", role=" + role +
        '}';
  }
}
