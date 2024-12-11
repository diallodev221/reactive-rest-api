package com.diallodev.reactiverestapi.user;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> save(UserRequest user);
    Mono<User> delete(String id);
    Mono<User> update(String id, UserRequest user);

    Flux<User> findAll();
    Mono<User> findById(String id);
}
