package com.library.interfaces.book;

public interface IssuedBook {
    int getId();
    int getUserId();
    int getBookId();
    void setId(int id);
    void setUserId(int userId);
    void setBookId(int bookId);
    String toString();
    boolean equals(Object object);
}
