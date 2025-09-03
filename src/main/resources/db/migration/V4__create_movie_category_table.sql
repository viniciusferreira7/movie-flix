CREATE TABLE movie_category (
    movie_id INTEGER,
    category_id INTEGER,
    CONSTRAINT fk_movie_category_movies FOREIGN KEY(movie_id) REFERENCES movies(id),
    CONSTRAINT fk_movie_category_categories FOREIGN KEY(category_id) REFERENCES categories(id)
);