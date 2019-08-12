package com.library.impls.book;

import com.library.interfaces.book.IssuedBook;

public class IssuedBookImpl implements IssuedBook {
    private int id;
    private int userId;
    private int bookId;

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getUserId() {
        return this.userId;
    }

    @Override
    public int getBookId() {
        return this.bookId;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
