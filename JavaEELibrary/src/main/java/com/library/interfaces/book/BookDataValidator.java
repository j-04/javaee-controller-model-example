package com.library.interfaces.book;

public interface BookDataValidator {
    boolean isBookNameValid(String name);
    boolean isBookAuthorNameValid(String authorName);
    boolean areBookPagesValid(String pages);
}
