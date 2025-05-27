package com.example.MyApp.Services;

import com.example.MyApp.Entities.Book;
import com.example.MyApp.Entities.Genre;
import com.example.MyApp.Repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre createGenre(Genre genre) {
        return genreRepository.insert(genre);
    }

    public void deleteGenre(String id) {
        genreRepository.deleteById(id);
    }

    public List<Genre> listAllGenres() {
        return genreRepository.findAll();
    }

    public Page<Genre> listAllGenres(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }

}
