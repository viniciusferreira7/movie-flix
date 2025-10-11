package com.movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record StreamingRequest(@NotEmpty(message = "Streaming's name is required.") String name) {
}
