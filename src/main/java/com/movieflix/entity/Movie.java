package com.movieflix.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String title;

    @Column(length = 300)
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    private double rating;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedAt;


}
