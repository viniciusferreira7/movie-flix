package com.movieflix.mapper;

import com.movieflix.controller.request.MovieRequest;
import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public static Movie toMovie(MovieRequest movieRequest){
        List<Category> categories = movieRequest
                .categories()
                .stream()
                .map(categoryId -> Category.builder().id(categoryId).build()).toList();

        List<Streaming> streaming = movieRequest
                .streaming()
                .stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build()).toList();

        return Movie.builder()
                .title(movieRequest.title())
                .description(movieRequest.description())
                .releaseDate(movieRequest.releaseDate())
                .rating(movieRequest.rating())
                .categories(categories)
                .streaming(streaming)
        .build();
    }
}
