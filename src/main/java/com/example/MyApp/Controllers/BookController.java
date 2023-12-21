package com.example.MyApp.Controllers;

import com.example.MyApp.Entities.Book;
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

    @PostMapping("/create")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @DeleteMapping("/delete/{id}")
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
           return bookService.save(bookData);
        }
        else {
            return null;
        }

    }

}
