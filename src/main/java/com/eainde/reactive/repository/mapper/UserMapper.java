package com.eainde.reactive.repository.mapper;

import com.eainde.reactive.entity.User;

import io.r2dbc.spi.Row;

import java.util.function.BiFunction;

import org.springframework.stereotype.Component;

@Component
public class UserMapper implements BiFunction<Row, Object, User> {

  @Override
  public User apply(Row row, Object o) {
    long id = row.get("user_id", Long.class);
    String country = row.get("country", String.class);
    long age = row.get("age", Long.class);
    String name = row.get("name", String.class);
    String password = row.get("password", String.class);
    boolean enabled = row.get("enabled", Boolean.class);
    String roleName = row.get("role_name", String.class);
    long roleId = row.get("role_id", Long.class);
    return new User(id, name, country, age, roleName, roleId, password);
  }
}
