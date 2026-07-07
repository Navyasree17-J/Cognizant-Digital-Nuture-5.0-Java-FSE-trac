package com.library.service;

import com.library.repository.BookRepository;
import java.util.List;

public class BookService {
    private BookRepository bookRepository;

    // Setter method for dependency injection (needed for Exercise 2/5 later)
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<String> getAvailableBooks() {
        return bookRepository.findAllBooks();
    }

    public void registerNewBook(String title) {
        bookRepository.addBook(title);
    }
}