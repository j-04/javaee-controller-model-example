package com.library.dao.interfaces;

import com.library.interfaces.book.IssuedBook;

import java.util.List;

public interface IssuedBookDAO {
    IssuedBook addNewIssuedBook(int userId, int bookId);
    List<IssuedBook> getIssuedBooks();
    IssuedBook getByUserIdAndBookId(int userId, int bookId);
    IssuedBook getById(int id);
    int deleteIssuedBook(int userId, int bookId);
}
