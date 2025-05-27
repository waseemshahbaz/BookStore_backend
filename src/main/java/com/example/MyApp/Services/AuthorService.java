package com.example.MyApp.Services;

import com.example.MyApp.Entities.Author;
import com.example.MyApp.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    public Page<Author> getPaginatedAuthors(Pageable pageable) {
        return this.authorRepository.findAll(pageable);
    }
}
