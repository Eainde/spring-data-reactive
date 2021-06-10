package com.eainde.reactive.repository;

import com.eainde.reactive.entity.User;
import com.eainde.reactive.repository.mapper.UserMapper;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserRepository {
  private final DatabaseClient client;
  private final UserMapper mapper;

  UserRepository(final DatabaseClient client, final UserMapper mapper) {
    this.client = client;
    this.mapper = mapper;
  }

  public Flux<User> findAll() {
    String query =
        "select u.user_id, u.country, u.age, u.name, aro.role_name, aro.role_id, u.password, u.enabled\n"
            + "from reactive.appl_user u left join reactive.user_role ur on u.user_id=ur.user_id\n"
            + "left join reactive.appl_role aro on aro.role_id=ur.role_id";

    return client.sql(query).map(mapper::apply).all();
  }

  public Flux<User> findById(long userId) {
    String query =
        "select u.user_id, u.country, u.age, u.name, aro.role_name, aro.role_id, u.password, u.enabled\n"
            + "from reactive.appl_user u inner join reactive.user_role ur on u.user_id=ur.user_id\n"
            + "inner join reactive.appl_role aro on aro.role_id=ur.role_id where u.user_id=:userId";

    return client.sql(query).bind("userId", userId).map(mapper::apply).all();
  }

  public Mono<User> findByName(String userName) {
    String query =
        "select u.user_id, u.country, u.age, u.name, aro.role_name, aro.role_id, u.password, u.enabled\n"
            + "from reactive.appl_user u inner join reactive.user_role ur on u.user_id=ur.user_id\n"
            + "inner join reactive.appl_role aro on aro.role_id=ur.role_id where u.name=:userName";

    return client.sql(query).bind("userName", userName).map(mapper::apply).first();
  }
}
