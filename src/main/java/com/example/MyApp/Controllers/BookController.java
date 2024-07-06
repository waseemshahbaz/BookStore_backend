package com.example.MyApp.Controllers;

import com.amazonaws.services.s3.AmazonS3Client;
import com.example.MyApp.AWSConfiguration;
import com.example.MyApp.Entities.Book;
import com.example.MyApp.Services.AmazonS3;
import com.example.MyApp.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public List<Book> listAllBooks() {
        return bookService.listAllBooks();
    }

    @GetMapping("/bookCovers")
    public List<byte[]> listAllBookCovers() {
        List<byte[]> listofBookCovers = bookService.getAllCoverImages();
        return listofBookCovers;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable String id) {
        Optional<Book> book = bookService.findById(id);
        return book.get();
    }

    @PostMapping("/create")
    public Book createBook(@RequestBody Book book) {
        Book returnObj = bookService.createBook(book);
        return returnObj;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/dummypage")
    public String dummyPage() {
        return "dummy string added more more";
    }

    @PutMapping("/update/{id}")
    public Book updateBook(@PathVariable String id, @RequestBody Book book) {
        Optional<Book> toBeUpdated = bookService.findById(id);
        if(toBeUpdated.isPresent()) {
           Book bookData = toBeUpdated.get();
           bookData.updateData(book);
           Book returnObj = bookService.save(bookData);
           return returnObj;
        }
        else {
            return null;
        }

    }

}
