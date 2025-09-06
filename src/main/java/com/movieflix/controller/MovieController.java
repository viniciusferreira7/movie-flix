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
            @ApiResponse(responseCode = "200", description = "Get movies")
    })
    @GetMapping
    public ResponseEntity<List<MovieResponse>> getMovies(){
        List<MovieResponse> moviesResponses = movieService.getMovies()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(moviesResponses);
    }
}
