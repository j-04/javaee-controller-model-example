package com.library.dao.impls;

import com.library.dao.interfaces.IssuedBookDAO;
import com.library.db.ConnectionDB;
import com.library.interfaces.book.IssuedBook;
import com.library.util.SHA256Encoder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class IssuedBookDAOImplTest {
    private IssuedBookDAO issuedBookDAO = new IssuedBookDAOImpl();

    @BeforeClass
    public static void setUpConnection() {
        DBUtils.setUpConnection();
    }

    @AfterClass
    public static void afterClassCloseConnections() {
        DBUtils.updateUserTable();
        DBUtils.updateBookTable();
        DBUtils.updateIssuedBookTable();
        DBUtils.closeConnections();
    }

    @Test
    public void getIssuedBooks() {
        List<IssuedBook> issuedBooks = issuedBookDAO.getIssuedBooks();
    }

    @Test
    public void getByUserIdAndBookId() {
    }

    @Test
    public void getById() {
    }

    private static void createData() {
        String createUserQuery = "insert into user (login, email, password, role)\n" +
                "VALUES (\"testLogin\", \"test@test.ru\", \"testpassword\",\"admin\");";
        String createBookQuery1 = "";
    }

    @Test
    public void addNewIssuedBook() {

    }

    @Test
    public void deleteIssuedBook() {

    }

    private void insertBookData (String name, String authorName, int pages, String resourcePath) {
        final String query = "insert into book (name, authorName, pages, resourcePath)\n" +
                "values (?, ?, ?, ?);";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = ConnectionDB.connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, authorName);
            preparedStatement.setInt(3, pages);
            preparedStatement.setString(4, resourcePath);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertUserData (String login, String email, String password, String role) {
        final String query = "insert into user (login, email, password, role)" +
                "values (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = ConnectionDB.connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, SHA256Encoder.encode(password));
            preparedStatement.setString(4, role);
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}