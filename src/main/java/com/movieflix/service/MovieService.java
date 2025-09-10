package com.movieflix.service;

import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;
import com.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public Movie create(Movie movie){
        movie.setCategories(this.findCategories(movie.getCategories()));
        return movieRepository.save(movie);
    }

    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }

    private List<Category> findCategories(List<Category> categories) {
        List<Category> categoriesFound = new ArrayList<>();

        categories.forEach(category ->
            categoryService.getById(category.getId())
                    .ifPresent(categoriesFound::add)
        );

        return categoriesFound;
    }

    private List<Streaming> findStreaming(List<Streaming> streaming) {
        List<Streaming> streamingFound = new ArrayList<>();

        streaming.forEach(item ->
            streamingService.getById(item.getId())
                    .ifPresent(streamingFound::add)
        );

        return streamingFound;
    }

    public Optional<Movie> getById(Long id){
        return movieRepository.findById(id);
    }

}
