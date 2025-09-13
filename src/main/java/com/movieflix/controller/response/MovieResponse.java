package com.movieflix.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse (
        Long id,
        String title,
        String description,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
        LocalDate releaseDate,

        Double rating,
        List<CategoryResponse> categories,
        List<StreamingResponse> streaming
 ) {}
