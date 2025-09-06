package com.movieflix.mapper;

import com.movieflix.controller.request.MovieRequest;
import com.movieflix.controller.response.CategoryResponse;
import com.movieflix.controller.response.MovieResponse;
import com.movieflix.controller.response.StreamingResponse;
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

    public static MovieResponse toMovieResponse(Movie movie){
        List<CategoryResponse> categoriesResponses = movie.getCategories()
                .stream()
                .map(category ->
                        CategoryResponse.builder()
                                .id(category.getId())
                                .name(category.getName()).build())
                .toList();

        List<StreamingResponse> streamingResponses = movie.getStreaming()
                .stream()
                .map(streaming ->
                        StreamingResponse.builder()
                                .id(streaming.getId())
                                .name(streaming.getName()).build())
                .toList();


        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .categories(categoriesResponses)
                .streaming(streamingResponses)
                .build();
    }
}
