package com.example.MyApp.Repositories;


import com.example.MyApp.Entities.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<Genre, String > {
}
