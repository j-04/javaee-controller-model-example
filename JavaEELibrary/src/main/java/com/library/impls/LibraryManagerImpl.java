package com.library.impls;

import com.library.dao.impls.BookDAOImpl;
import com.library.dao.impls.IssuedBookDAOImpl;
import com.library.dao.impls.UserDAOImpl;
import com.library.dao.interfaces.BookDAO;
import com.library.dao.interfaces.IssuedBookDAO;
import com.library.dao.interfaces.UserDAO;
import com.library.impls.book.BookDataValidatorImpl;
import com.library.impls.user.UserCredentialValidatorImpl;
import com.library.interfaces.LibraryManager;
import com.library.interfaces.book.Book;
import com.library.interfaces.book.BookDataValidator;
import com.library.interfaces.book.IssuedBook;
import com.library.interfaces.user.User;
import com.library.interfaces.user.UserCredentialValidator;
import com.library.util.SHA256Encoder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryManagerImpl implements LibraryManager {
    private BookDataValidator bookDataValidator;
    private UserCredentialValidator userCredentialValidator;
    private IssuedBookDAO issuedBookDAO;
    private BookDAO bookDAO;
    private UserDAO userDAO;

    public LibraryManagerImpl() {
        this.bookDataValidator = new BookDataValidatorImpl();
        this.userCredentialValidator = new UserCredentialValidatorImpl();
        this.issuedBookDAO = new IssuedBookDAOImpl();
        this.bookDAO = new BookDAOImpl();
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public List<String> registration(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        User user = null;
        String login = request.getParameter("userLogin");
        String email = request.getParameter("userEmail");
        String password = request.getParameter("userPassword");
        List<String> errors = new ArrayList<>();

        boolean isLoginCorrect = userCredentialValidator.isUserLoginValid(login);
        boolean isEmailCorrect = userCredentialValidator.isUserEmailValid(email);
        boolean isPasswordCorrect = userCredentialValidator.isUserPasswordValid(password);

        if (!isLoginCorrect) errors.add("The login is incorrect!");
        if (!isEmailCorrect) errors.add("The email is incorrect!");
        if (!isPasswordCorrect) errors.add("The password is incorrect!");

        if (errors.size() == 0) {
            try {
                password = SHA256Encoder.encode(password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            user = userDAO.addNewUser(login, email, password, "user");
            session.setAttribute("login", user.getLogin());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("password", user.getPassword());
            session.setAttribute("role", user.getRole());
            session.setAttribute("logged", true);
        }
        return errors;
    }

    @Override
    public List<String> authorisation(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        User user = null;
        String login = request.getParameter("userLogin");
        String password = request.getParameter("userPassword");
        List<String> errors = new ArrayList<>();

        boolean isLoginCorrect = userCredentialValidator.isUserLoginValid(login);
        boolean isPasswordCorrect = userCredentialValidator.isUserPasswordValid(password);

        if (!isLoginCorrect) errors.add("The login is incorrect!");
        if (!isPasswordCorrect) errors.add("The password is incorrect!");

        if (errors.size() == 0) {
            try {
                password = SHA256Encoder.encode(password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            user = userDAO.getByCredentials(login, password);
            session.setAttribute("login", user.getLogin());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("password", user.getPassword());
            session.setAttribute("role", user.getRole());
            session.setAttribute("logged", true);
        }
        return errors;
    }

    @Override
    public List<String> deleteCurrentUser(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        User user = null;
        String login = request.getParameter("userLogin");
        String password = request.getParameter("userPassword");
        List<String> errors = new ArrayList<>();

        boolean isLoginCorrect = userCredentialValidator.isUserLoginValid(login);
        boolean isPasswordCorrect = userCredentialValidator.isUserPasswordValid(password);

        if (!isLoginCorrect) errors.add("The login is incorrect!");
        if (!isPasswordCorrect) errors.add("The password is incorrect!");

        if (errors.size() == 0) {
            try {
                password = SHA256Encoder.encode(password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            int deleteResult = userDAO.deleteByLogin(login, password);
            if (deleteResult < 0) errors.add("Error during delete operation!");
        }
        return errors;
    }

    @Override
    public boolean deleteUser(HttpServletRequest request, HttpServletResponse response) {
        return false;
    }

    @Override
    public List<String> registrationOfNewBook(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String authorName = request.getParameter("authorName");
        String pagesText = request.getParameter("pages");
        List<String> errors = new ArrayList<>();

        boolean isNameValid = bookDataValidator.isBookNameValid(name);
        boolean isAuthorNameValid = bookDataValidator.isBookAuthorNameValid(authorName);
        boolean arePagesValid = bookDataValidator.areBookPagesValid(pagesText);

        if (!isNameValid) errors.add("The name is incorrect!");
        if (!isAuthorNameValid) errors.add("The authorName is incorrect");
        if (!arePagesValid) errors.add("The page entry form is incorrect");

        if (errors.size() == 0) {
            int pages = Integer.parseInt(pagesText);
            bookDAO.addNewBook(name, authorName, pages, "");
        }
        return errors;
    }

    @Override
    public List<String> issueBook(HttpServletRequest request, HttpServletResponse response) {
        String userId = (String) request.getSession().getAttribute("id");
        String bookId = request.getParameter("bookId");
        List<String> errors = new ArrayList<>();

        boolean isUserIdCorrect = StringUtils.isNumeric(userId);
        boolean isBookIdCorrect = StringUtils.isNumeric(bookId);

        if (!isUserIdCorrect) errors.add("The userId is incorrect!");
        if (!isBookIdCorrect) errors.add("The bookId is incorrect!");

        IssuedBook issuedBook = null;

        if (errors.size() == 0) {
            int userid = NumberUtils.createInteger(userId);
            int bookid = NumberUtils.createInteger(bookId);
            issuedBook = issuedBookDAO.addNewIssuedBook(userid, bookid);
        }

        if (issuedBook == null) errors.add("The book is already issued!");
        return errors;
    }

    @Override
    public Map<Integer, Book> getAllIssuedBooks() {
        List<IssuedBook> issuedBooks = issuedBookDAO.getIssuedBooks();
        Map<Integer, Book> books = new HashMap<>();
        issuedBooks.forEach(issuedBook -> {
            Book book = bookDAO.getById(issuedBook.getBookId());
            books.put(issuedBook.getUserId(), book);
        });
        return books;
    }
}
