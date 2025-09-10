package com.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movieflix.entity.Streaming;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(
        String title,
        String description,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
        LocalDate releaseDate,
        
        double rating,
        List<Long> categories,
        List<Long> streaming
) {
}
