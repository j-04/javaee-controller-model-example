package com.library.dao.interfaces;

import com.library.interfaces.book.Book;

import java.util.List;

public interface BookDAO {
    Book addNewBook(String name, String authorName, int pages, String resourcePath);
    Book getById(int id);
    Book getByName(String name);
    List<Book> getByAuthor(String authorName);
    List<Book> getIssuedBooks();
    List<Book> getBooks();
    int deleteById(int id);
}
