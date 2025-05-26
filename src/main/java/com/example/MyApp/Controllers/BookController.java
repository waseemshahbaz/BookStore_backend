package com.example.MyApp.Controllers;

import com.amazonaws.services.s3.AmazonS3Client;
import com.example.MyApp.AWSConfiguration;
import com.example.MyApp.Entities.Book;
import com.example.MyApp.Services.AmazonS3;
import com.example.MyApp.Services.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import static java.util.Objects.nonNull;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    @Autowired
    private AmazonS3 amazonS3Client;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /*@GetMapping("/")
    public List<Book> listAllBooks() {
        return bookService.listAllBooks();
    }*/

    @GetMapping("/")
    public Page<Book> listPaginatedBooks(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(required = false) String searchItem)
    {
        if (searchItem.length() >= 3) {
            Pageable pageable = PageRequest.of(page, size);
            return bookService.searchBooks(searchItem, searchItem, pageable);

        }
        else {
            Pageable pageable = PageRequest.of(page, size);
            return bookService.listAllPaginatedBooks(pageable);
        }

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

    @PostMapping("/save")
    public  Book saveBook(@RequestPart(required = false) Book book, @RequestPart(required = false) MultipartFile cover) {
        Book bookToBeSaved = new Book();
        bookToBeSaved.updateData(book);
        if (nonNull(cover) && !cover.isEmpty()) {
            String filename = amazonS3Client.uploadPicture(cover);
            if (!filename.isEmpty()) {
                bookToBeSaved.setcoverImageURL(filename);
            }
        }
        return bookService.createBook(bookToBeSaved);
    }

    @PutMapping("/update/{id}")
    public Book updateBook(@PathVariable String id, @RequestPart(required = false) Book book, @RequestPart(required = false) MultipartFile cover) {
        Optional<Book> toBeUpdated = bookService.findById(id);
        if(toBeUpdated.isPresent()) {
           Book bookData = toBeUpdated.get();
           bookData.updateData(book);
           if (!cover.isEmpty()) {
               String filename = amazonS3Client.uploadPicture(cover);
               if (!filename.isEmpty()) {
                   bookData.setcoverImageURL(filename);
               }
           }
           Book returnObj = bookService.save(bookData);
           return returnObj;
        }
        else {
            return null;
        }

    }

}
