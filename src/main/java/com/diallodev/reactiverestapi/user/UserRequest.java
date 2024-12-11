package com.diallodev.reactiverestapi.user;

import lombok.Builder;

@Builder
public record UserRequest(
        String name,
        String email,
        String password
) {
}
