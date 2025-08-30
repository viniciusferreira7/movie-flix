CREATE TABLE movie (
    id serial PRIMARY KEY,
    title varchar(255) NOT NULL,
    description varchar(500),
    release_date date,
    rating numeric,
    created_at timestamp,
    updated_at timestamp
);