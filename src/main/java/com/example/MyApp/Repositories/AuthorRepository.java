package com.example.MyApp.Repositories;

import com.example.MyApp.Entities.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
