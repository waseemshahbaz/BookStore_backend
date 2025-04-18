package com.example.MyApp.Repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.MyApp.Entities.Book;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    @Query(value = "{}", fields = "{ 'coverImageURL' : 1 , '_id' : 2 }")
    public List<Book> listAllBooksByCoverImage();
    Page<Book> findByTitleLikeIgnoreCaseOrGenreLikeIgnoreCase(String title, String genre, Pageable pageable);
}
