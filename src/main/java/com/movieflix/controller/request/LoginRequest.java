package com.movieflix.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(

        @NotEmpty(message = "Email is required.")
        @Email(message = "Email must be valid.")
        String email,

        @NotEmpty(message = "Password is required.")
        String password
) {}
