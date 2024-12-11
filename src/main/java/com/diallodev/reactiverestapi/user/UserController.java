package com.diallodev.reactiverestapi.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> save(@RequestBody UserRequest user) {
        return this.userService.save(user);
    }

    @GetMapping
    public Flux<User> findAll() {
        return this.userService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> findById(@PathVariable("id") String userId) {
        return userService.findById(userId)
                .flatMap(user -> Mono.just(ResponseEntity.ok(user)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> update(@PathVariable("id") String userId, @RequestBody UserRequest user) {
        return userService.update(userId, user)
                .flatMap(response -> Mono.just(ResponseEntity.ok(response)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<String>> delete(@PathVariable("id") String userId) {
        return this.userService.delete(userId)
                .flatMap(user -> Mono.just(ResponseEntity
                        .ok("Deleted Successfully")))
                .switchIfEmpty(Mono.just(ResponseEntity
                        .notFound().build()));
    }
}
