package com.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public record MovieRequest(

        @NotEmpty(message = "Title is required.")
        String title,

        @NotEmpty(message = "Description is required.")
        String description,

        @NotNull(message = "Release date is required.")
        LocalDate releaseDate,

        @NotNull(message = "Rating is required.")
        double rating,

        @NotEmpty(message = "At least one category is required.")
        List<Long> categories,

        @NotEmpty(message = "At least one streaming service is required.")
        List<Long> streaming
) {}
