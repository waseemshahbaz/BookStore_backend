package com.example.MyApp.Entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Book")
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private String isbn;
    private double price;
    private String language;
    private String publisher;
    private int numberOfPages;
    private List<Double> ratings;
    private String summary;
    private List<String> tags;
    private String coverImage;

    public Book(String id, String title, String author, String genre, int publicationYear, String isbn, double price, String language, String publisher, int numberOfPages, List<Double> ratings, String summary, List<String> tags, String coverImage) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.price = price;
        this.language = language;
        this.publisher = publisher;
        this.numberOfPages = numberOfPages;
        this.ratings = ratings;
        this.summary = summary;
        this.tags = tags;
        this.coverImage = coverImage;
    }

    public void updateData(Book object) {
        this.setAuthor(object.getAuthor());
        this.setGenre(object.getGenre());
        this.setIsbn(object.getIsbn());
        this.setLanguage(object.getLanguage());
        this.setCoverImage(object.getCoverImage());
        this.setNumberOfPages(object.getNumberOfPages());
        this.setPrice(object.getPrice());
        this.setPublicationYear(object.getPublicationYear());
        this.setPublisher(object.getPublisher());
        this.setRatings(object.getRatings());
        this.setSummary(object.getSummary());
        this.setTags(object.getTags());
        this.setTitle(object.getTitle());
    }

    public Book() {
    }

    public Book(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<Double> getRatings() {
        return ratings;
    }

    public void setRatings(List<Double> ratings) {
        this.ratings = ratings;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
