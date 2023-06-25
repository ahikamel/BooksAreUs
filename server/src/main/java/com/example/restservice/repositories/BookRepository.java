package com.example.restservice.repositories;

import com.example.restservice.models.Book;
import com.example.restservice.repositories.utils.RepositoryUtils;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookRepository implements IBookRepository {
    private final RepositoryUtils repoUtil;

    private final String _booksDataFilePath = "./src/main/resources/data/books-data.json";
    private final String _dbName = "books";

    private List<Book> _booksList;
    private Map<UUID, Book> _booksMap;

    public BookRepository(RepositoryUtils repoUtil) {
        this.repoUtil = repoUtil;
    }

    public Book getBookById(UUID bookId) throws Exception {
        checkBooksDbConnectivity();
        return booksMapById().get(bookId);
    }

    public Book getBook(int index) throws Exception {
        checkBooksDbConnectivity();
        return _booksList.get(index);
    }

    public List<Book> getBooks(int startIndex, int endIndex) throws Exception {
        checkBooksDbConnectivity();
        return _booksList.subList(startIndex, endIndex);
    }

    public List<Book> getTopBooksByRating(int count) throws Exception {
        checkBooksDbConnectivity();
        _booksList.sort(Comparator.comparing(book -> book.rating, Collections.reverseOrder()));
        return _booksList.subList(0, Math.min(count, _booksList.size()));
    }

    public int getTotalBooksCount() throws Exception {
        checkBooksDbConnectivity();
        return _booksList.size();
    }

    //TODO: Add cache
    private Map<UUID, Book> booksMapById() throws Exception {
        if (_booksMap == null) {
            if (checkBooksDbConnectivity()){
                try {
                    _booksMap = _booksList.stream().collect(Collectors.toMap(book -> book.id, book -> book));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return _booksMap;
    }

    private boolean checkBooksDbConnectivity() throws Exception {
        Type BOOK_TYPE = new TypeToken<List<Book>>(){}.getType();
        _booksList = repoUtil.checkDbConnectivity(_booksList, _dbName, _booksDataFilePath, BOOK_TYPE);
        return true;
    }
}
