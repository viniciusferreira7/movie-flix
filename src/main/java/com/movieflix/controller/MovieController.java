package com.movieflix.controller;

import com.movieflix.controller.request.MovieRequest;
import com.movieflix.entity.Movie;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @Operation(summary = "Create register of movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movie was successfully registered")
    })
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody MovieRequest movieRequest){
        Movie movie = MovieMapper.toMovie(movieRequest);

        movieService.create(movie);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
