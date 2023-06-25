package com.example.restservice.models;

import java.time.LocalDate;
import java.util.UUID;

public class Book {
    public UUID id;
    public String title;
    public String author;
    public LocalDate publishingDate;
    public float rating;
    public String image;

    public Book(String title){
        this.title = title;
    }

    public Book(String title, String author, LocalDate publishingDate, float rating, String image){
        this.title = title;
        this.author = author;
        this.publishingDate = publishingDate;
        this.rating = rating;
        this.image = image;
    }
}
