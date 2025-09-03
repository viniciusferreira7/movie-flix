package com.movieflix.service;

import com.movieflix.entity.Movie;
import com.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public Movie create(Movie movie){
        return movieRepository.save(movie);
    }
}
