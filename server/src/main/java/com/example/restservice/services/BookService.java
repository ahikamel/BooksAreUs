package com.example.restservice.services;

import com.example.restservice.models.Book;
import com.example.restservice.repositories.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BookService implements IBookService{
    @Autowired
    private IBookRepository _bookRepository;

    public Book getBookById(UUID bookId) throws Exception {
        return _bookRepository.getBookById(bookId);
    }

    public List<Book> getBooks(int pageNum, int pageSize) throws Exception {
        int startIndex = pageSize * (pageNum-1);
        int totalBooksCount = getTotalBooksCount();

        if (startIndex < totalBooksCount){
            int endIndex = startIndex + Math.min(pageSize, totalBooksCount-startIndex);
            return _bookRepository.getBooks(startIndex, endIndex);
        }
        throw new Exception("Could not retrieve books for page #" + pageNum);
    }

    public List<Book> getTopBooksByRating(int count) throws Exception {
        return _bookRepository.getTopBooksByRating(count);
    }

    public Book getDailyBook() throws Exception {
        // Uncomment below to increase the daily book cycle to 24 hours (currently changes every 1 minute).
        int index = (int) (new Date().getTime() / (/*24 * 60 * */ 60 * 1000)) % getTotalBooksCount();
        return _bookRepository.getBook(index);
    }
    public int getTotalBooksCount() throws Exception {
        return _bookRepository.getTotalBooksCount();
    }
}
