package com.library.dao.impls;

import com.library.dao.interfaces.IssuedBookDAO;
import com.library.impls.book.IssuedBookImpl;
import com.library.interfaces.book.IssuedBook;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.library.db.ConnectionDB.connection;

public class IssuedBookDAOImpl implements IssuedBookDAO {
    private static Logger log = Logger.getLogger(IssuedBookDAOImpl.class.getName());

    @Override
    public IssuedBook addNewIssuedBook(int userId, int bookId) {
        final String query = "insert into issuedbook (userid, bookid)" +
                "values (?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return null;
        }

        return this.getByUserIdAndBookId(userId, bookId);
    }

    @Override
    public List<IssuedBook> getIssuedBooks() {
        final String query = "select * from issuedbook;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<IssuedBook> resultIssuedBooks = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                IssuedBook issuedBook = parseResultSet(resultSet);
                if (issuedBook != null)
                    resultIssuedBooks.add(issuedBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultIssuedBooks;
    }

    @Override
    public IssuedBook getByUserIdAndBookId(int userId, int bookId) {
        final String query = "select * from issuedbook where userid = ? and bookid = ?;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        IssuedBook resultIssuedBook = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultIssuedBook = parseResultSet(resultSet);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return null;
        }
        return resultIssuedBook;
    }

    @Override
    public IssuedBook getById(int id) {
        final String query = "select * from issuedbook where id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        IssuedBook issuedBook = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            issuedBook = parseResultSet(resultSet);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return null;
        }
        return issuedBook;
    }

    @Override
    public int deleteIssuedBook(int userId, int bookId) {
        final String query = "delete from issuedbook where userid = ? and bookid = ?;";
        PreparedStatement preparedStatement = null;
        int resultInt = -1;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultInt = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return -1;
        }
        return resultInt;
    }

    private IssuedBook parseResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.getRow() == 0) {
            IssuedBook issuedBook = new IssuedBookImpl();
            issuedBook.setId(resultSet.getInt("id"));
            issuedBook.setUserId(resultSet.getInt("userid"));
            issuedBook.setBookId(resultSet.getInt("bookid"));
            return issuedBook;
        }
        return null;
    }
}
