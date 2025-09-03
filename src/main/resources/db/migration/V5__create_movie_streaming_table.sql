CREATE TABLE movie_streaming (
    movie_id INTEGER,
    streaming_id INTEGER,
    CONSTRAINT fk_movie_streaming_movies FOREIGN KEY(movie_id) REFERENCES movies(id),
    CONSTRAINT fk_movie_streaming_streaming FOREIGN KEY(streaming_id) REFERENCES streaming(id)
);