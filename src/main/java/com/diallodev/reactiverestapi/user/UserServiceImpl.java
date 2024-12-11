package com.diallodev.reactiverestapi.user;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<User> save(UserRequest user) {
        var newUser = new User(user.name(), user.email(), user.password());
        return repository.save(newUser);
    }

    @Override
    public Mono<User> delete(String id) {
        return repository.findById(id)
                .flatMap(user -> repository.
                        deleteById(user.getId())
                        .thenReturn(user));
    }

    @Override
    public Mono<User> update(String id, UserRequest user) {
        return repository.findById(id)
                .flatMap(u -> {
                    u.setId(u.getId());
                    u.setName(user.name());
                    u.setEmail(user.email());
                    u.setPassword(user.password());
                    return repository.save(u);
                }).switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<User> findById(String id) {
        return repository.findById(id);
    }
}
