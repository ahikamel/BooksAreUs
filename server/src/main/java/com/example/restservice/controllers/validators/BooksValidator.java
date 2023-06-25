package com.example.restservice.controllers.validators;

import com.example.restservice.repositories.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BooksValidator implements IBooksValidator{

    @Autowired
    private IBookRepository _bookRepository;

    public boolean isPageNumberValid(int pageNumber, int pageSize) throws Exception {
        return 1 <= pageNumber &&
                pageNumber <= Math.ceil((float)_bookRepository.getTotalBooksCount() / (float)pageSize);
    }
}
