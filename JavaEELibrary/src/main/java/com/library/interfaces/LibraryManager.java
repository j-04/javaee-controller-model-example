package com.library.interfaces;

import com.library.interfaces.book.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface LibraryManager {
    List<String> registration(HttpSession session, HttpServletRequest request, HttpServletResponse response);
    List<String> authorisation(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response);
    List<String> deleteCurrentUser(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response);
    boolean deleteUser(HttpServletRequest request, HttpServletResponse response);
    List<String> registrationOfNewBook(HttpServletRequest request, HttpServletResponse response);
    List<String> issueBook(HttpServletRequest request, HttpServletResponse response);
    Map<Integer, Book> getAllIssuedBooks();
}
