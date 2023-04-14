package com.example.BookManagement.Exception;

import java.util.NoSuchElementException;

public class FailedAttemptException extends NoSuchElementException {
    public FailedAttemptException(){
        super("The Author doesn't exist in the database.");
    }
}
