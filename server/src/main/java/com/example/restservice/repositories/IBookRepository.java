package com.example.restservice.repositories;

import com.example.restservice.models.Book;
import java.util.List;
import java.util.UUID;

public interface IBookRepository {
    Book getBookById(UUID bookId) throws Exception;
    Book getBook(int index) throws Exception;
    List<Book> getBooks(int startIndex, int count) throws Exception;
    List<Book> getTopBooksByRating(int count) throws Exception;
    int getTotalBooksCount() throws Exception;
}
