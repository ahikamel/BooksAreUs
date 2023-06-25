package com.example.restservice.services;

import com.example.restservice.models.Book;
import java.util.List;
import java.util.UUID;

public interface IBookService {
    Book getBookById(UUID bookId) throws Exception;
    List<Book> getBooks(int pageNum, int pageSize) throws Exception;
    List<Book> getTopBooksByRating(int count) throws Exception;
    Book getDailyBook() throws Exception;
    int getTotalBooksCount() throws Exception;
}
