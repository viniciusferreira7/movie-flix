package com.movieflix.controller;

import com.movieflix.controller.request.MovieRequest;
import com.movieflix.controller.response.MovieResponse;
import com.movieflix.entity.Movie;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @Operation(summary = "Get all movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return movies")
    })
    @GetMapping
    public ResponseEntity<List<MovieResponse>> getMovies(){
        List<MovieResponse> moviesResponses = movieService.getMovies()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(moviesResponses);
    }

    @Operation(summary = "Get movie by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return movie"),
            @ApiResponse(responseCode = "4004", description = "Movie not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovies(@PathVariable Long id){
        Optional<Movie> movie = movieService.getById(id);

        return movie.map(value ->
                ResponseEntity.ok(MovieMapper.toMovieResponse(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }
}
