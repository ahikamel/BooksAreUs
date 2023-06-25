package com.example.restservice.controllers.validators;

public interface IBooksValidator {
    boolean isPageNumberValid(int pageNumber, int pageSize) throws Exception;
}
