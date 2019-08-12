package com.library.dao.impls;

import com.library.dao.interfaces.BookDAO;
import com.library.impls.book.BookImpl;
import com.library.interfaces.book.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.library.db.ConnectionDB.*;

public class BookDAOImpl implements BookDAO {
    private static Logger log = Logger.getLogger(BookDAO.class.getName());

    public BookDAOImpl() {
    }

    @Override
    public Book addNewBook(String name, String authorName, int pages, String resourcePath) {
        final String query = "insert into book (name, authorName, pages, resourcePath) " +
                "values (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, authorName);
            preparedStatement.setInt(3, pages);
            preparedStatement.setString(4, resourcePath);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return null;
        }

        return this.getByName(name);
    }

    @Override
    public Book getById(int id) {
        String query = "select * from library.book where id = ? limit 1";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Book resultBook = new BookImpl();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            resultBook = parseResultSet(resultSet);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return null;
        }
        return resultBook;
    }

    @Override
    public Book getByName(String name) {
        String query = "select * from book where name = ? limit 1";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Book resultBook = new BookImpl();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            resultBook = parseResultSet(resultSet);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return null;
        }
        return resultBook;
    }

    @Override
    public List<Book> getByAuthor(String authorName) {
        String query = "select * from book where authorName = ?";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        List<Book> books = new ArrayList<Book>();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, authorName);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                books.add(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return null;
        }
        return books;
    }

    @Override
    public List<Book> getIssuedBooks() {
        return null;
    }

    @Override
    public List<Book> getBooks() {
        String query = "select * from book";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        List<Book> books = new ArrayList<Book>();
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                books.add(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return null;
        }
        return books;
    }

    @Override
    public int deleteById(int id) {
        String query = "delete from book where id = ?";
        PreparedStatement preparedStatement = null;
        int isDeleted = -1;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            isDeleted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return -1;
        }
        return isDeleted;
    }

    private Book parseResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.getRow() != 0) {
            Book resultBook = new BookImpl();
            resultBook.setId(resultSet.getInt("id"));
            resultBook.setName(resultSet.getString("name"));
            resultBook.setAuthorName(resultSet.getString("authorName"));
            resultBook.setPages(resultSet.getInt("pages"));
            resultBook.setResourcePath(resultSet.getString("resourcePath"));
            return resultBook;
        }
        return null;
    }
}
