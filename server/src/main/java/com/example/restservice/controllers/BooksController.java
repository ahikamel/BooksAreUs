package com.example.restservice.controllers;

import com.example.restservice.controllers.validators.IBooksValidator;
import com.example.restservice.models.Book;
import com.example.restservice.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:3000"})   // TODO: Move local host concrete address to a configuration file
@RestController
@RequestMapping("/book")
public class BooksController {

    @Autowired
    private IBookService _bookService;
    @Autowired
    private IBooksValidator _booksValidator;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(@RequestParam(name = "pageNum") int pageNum, @RequestParam(name = "pageSize") int pageSize) throws Exception {
        try{
            if (!_booksValidator.isPageNumberValid(pageNum, pageSize)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            List<Book> books = _bookService.getBooks(pageNum, pageSize);

            if (books == null)
                throw new Exception();
            if (books.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            else
                return ResponseEntity.status(HttpStatus.OK).body(books);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/book")
    public ResponseEntity<Book> getBookById(@RequestParam(name = "id") String bookId) {
        try{
            Book book = _bookService.getBookById(UUID.fromString(bookId));

            if (book == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            return ResponseEntity.status(HttpStatus.OK).body(book);
        }
        catch(Exception e){
            if (e.getMessage().contains("Invalid UUID string")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/recommended")
    public ResponseEntity<List<Book>> getRecommendedBooks(@RequestParam(name = "count") int count) {
        try{
            List<Book> topBooks = _bookService.getTopBooksByRating(count);

            if (topBooks == null)
                throw new Exception();
            if (topBooks.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            else
                return ResponseEntity.status(HttpStatus.OK).body(topBooks);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/daily")
    public ResponseEntity<Book> getDailyBook() {
        try{
            Book book = _bookService.getDailyBook();

            if (book == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            return ResponseEntity.status(HttpStatus.OK).body(book);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/totalBooksCount")
    public ResponseEntity<Integer> getTotalBooksCount() {
        try{
            int totalBooksCount = _bookService.getTotalBooksCount();
            return ResponseEntity.status(HttpStatus.OK).body(totalBooksCount);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
