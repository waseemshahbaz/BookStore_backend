package com.example.MyApp.Services;
import com.example.MyApp.Entities.Book;
import com.example.MyApp.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.insert(book);
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public List<byte[]> getAllCoverImages() {
        List<Book> coverImages =  bookRepository.listAllBooksByCoverImage();
        List<byte[]> files = new ArrayList<>();
        for(Book cover : coverImages) {
            String coverImage = cover.getcoverImageURL();
            if (amazonS3.objectExists(coverImage)) {
                byte[] file = amazonS3.downloadObject(coverImage);
                files.add(file);
            }
        }
        return files;
    }

    public Page<Book> listAllPaginatedBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
    public Page<Book> searchBooks(String title, String genre, Pageable pageable) {
        // If title or genre is null or empty, set them to empty strings to match all records
        if (title == null) title = "";
        if (genre == null) genre = "";

        return bookRepository.findByTitleLikeIgnoreCaseOrGenreLikeIgnoreCase(title, genre, pageable);
    }

}
