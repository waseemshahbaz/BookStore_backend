package com.example.MyApp.Repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.MyApp.Entities.Book;
public interface BookRepository extends MongoRepository<Book, String> {
}
