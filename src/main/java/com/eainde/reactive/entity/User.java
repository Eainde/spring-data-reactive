package com.eainde.reactive.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Table("appl_user")
public class User implements UserDetails {
  @Id private long id;

  @Column(value = "name")
  private String userName;

  private String country;
  private long age;
  private String password;
  private Boolean enabled;
  private List<Role> roles;

  public User() {}

  public User(
      long id, String userName, String country, long age, String password, List<Role> roles) {
    this.id = id;
    this.userName = userName;
    this.country = country;
    this.age = age;
    this.password = password;
    this.roles = roles;
  }

  public User(
      long id,
      String userName,
      String country,
      long age,
      String role,
      long roleId,
      String password) {
    this.id = id;
    this.userName = userName;
    this.country = country;
    this.age = age;
    this.password = password;
    List<Role> roleList = new ArrayList<>();
    roleList.add(new Role(roleId, role));
    this.roles = roleList;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public long getAge() {
    return age;
  }

  public void setAge(long age) {
    this.age = age;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  @JsonIgnore
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles
        .stream()
        .map(authority -> new SimpleGrantedAuthority(authority.getRoleName()))
        .collect(Collectors.toList());
  }

  @JsonIgnore
  @Override
  public String getPassword() {
    return password;
  }

  @JsonProperty
  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }
}
