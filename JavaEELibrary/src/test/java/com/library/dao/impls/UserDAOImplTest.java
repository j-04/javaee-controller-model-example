package com.library.dao.impls;

import com.library.dao.interfaces.UserDAO;
import com.library.impls.user.UserImpl;
import com.library.interfaces.user.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static com.library.dao.impls.DBUtils.closeConnections;
import static com.library.dao.impls.DBUtils.updateUserTable;
import static com.library.db.ConnectionDB.connection;
import static org.junit.Assert.assertEquals;

public class UserDAOImplTest {
    private UserDAO userDAO = new UserDAOImpl();

    @BeforeClass
    public static void setUpConnection() {
        setUpConnection();
    }

    @AfterClass
    public static void afterClassCloseConnections() {
        updateUserTable();
        closeConnections();
    }

    @Before
    public void beforeUpdateUserTable() {
        updateUserTable();
    }

    @Test
    public void getById() {
        final int id = 1;
        userGenerator("testLogin", "test@test.ru", "testpswd", "admin");

        User expectedUser = new UserImpl();
        expectedUser.setId(id);
        expectedUser.setLogin("testLogin");
        expectedUser.setEmail("test@test.ru");
        expectedUser.setPassword("testpswd");
        expectedUser.setRole("admin");

        User actualUser = userDAO.getById(id);

        System.out.println("GetById test:");
        assertEquals(expectedUser, actualUser);
        System.out.println("Expected: " + expectedUser);
        System.out.println("Actual: " + actualUser);
    }

    @Test
    public void getByLogin() {
        final String login = "testLogin";
        userGenerator(login, "test@test.ru", "testpswd", "admin");

        User expectedUser = new UserImpl();
        expectedUser.setId(1);
        expectedUser.setLogin(login);
        expectedUser.setEmail("test@test.ru");
        expectedUser.setPassword("testpswd");
        expectedUser.setRole("admin");

        User actualUser = userDAO.getByLogin(login);

        System.out.println("GetByLogin test:");
        assertEquals(expectedUser, actualUser);
        System.out.println("Expected: " + expectedUser);
        System.out.println("Actual: " + actualUser);
    }

    @Test
    public void getByEmail() {
        final String email = "test@test.ru";
        userGenerator("testLogin", email, "testpswd", "admin");

        User expectedUser = new UserImpl();
        expectedUser.setId(1);
        expectedUser.setLogin("testLogin");
        expectedUser.setEmail(email);
        expectedUser.setPassword("testpswd");
        expectedUser.setRole("admin");

        User actualUser = userDAO.getByEmail(email);

        System.out.println("GetByEmail test:");
        assertEquals(expectedUser, actualUser);
        System.out.println("Expected: " + expectedUser);
        System.out.println("Actual: " + actualUser);
    }

    @Test
    public void deleteById() {
        final int id = 1;
        userGenerator("testLogin", "test@test.ru", "testpswd", "admin");

        List<User> expectedUsers = Collections.emptyList();
        userDAO.deleteById(id);
        List<User> actualUsers = userDAO.getUsers();

        System.out.println("DeleteById test:");
        assertEquals(expectedUsers, actualUsers);
        System.out.println("Expected: " + expectedUsers);
        System.out.println("Actual: " + actualUsers);
    }

    @Test
    public void deleteByLogin() {
        final String login = "testLogin";
        userGenerator("testLogin", "test@test.ru", "testpswd", "admin");

        List<User> expectedUsers = Collections.emptyList();
        userDAO.deleteByLogin(login);
        List<User> actualUsers = userDAO.getUsers();

        System.out.println("DeleteByLogin test:");
        assertEquals(expectedUsers, actualUsers);
        System.out.println("Expected: " + expectedUsers);
        System.out.println("Actual: " + actualUsers);
    }

    @Test
    public void deleteByEmail() {
        final String email = "test@test.ru";
        userGenerator("testLogin", "test@test.ru", "testpswd", "admin");

        List<User> expectedUsers = Collections.emptyList();
        userDAO.deleteByEmail(email);
        List<User> actualUsers = userDAO.getUsers();

        System.out.println("DeleteByEmail test:");
        assertEquals(expectedUsers, actualUsers);
        System.out.println("Expected: " + expectedUsers);
        System.out.println("Actual: " + actualUsers);
    }

    @Test
    public void testDeleteByLogin() {
        final String login = "testLogin";
        final String password = "testpswd";
        userGenerator("testLogin", "test@test.ru", "testpswd", "admin");

        List<User> expectedUsers = Collections.emptyList();
        userDAO.deleteByLogin(login, password);
        List<User> actualUsers = userDAO.getUsers();

        System.out.println("TestDeleteByLogin test:");
        assertEquals(expectedUsers, actualUsers);
        System.out.println("Expected: " + expectedUsers);
        System.out.println("Actual: " + actualUsers);
    }

    @Test
    public void testDeleteByEmail() {
        final String email = "test@test.ru";
        final String password = "testpswd";
        userGenerator("testLogin", "test@test.ru", "testpswd", "admin");

        List<User> expectedUsers = Collections.emptyList();
        userDAO.deleteByEmail(email, password);
        List<User> actualUsers = userDAO.getUsers();

        System.out.println("TestDeleteByEmail test:");
        assertEquals(expectedUsers, actualUsers);
        System.out.println("Expected: " + expectedUsers);
        System.out.println("Actual: " + actualUsers);
    }

    @Test
    public void changeLogin() {
        final String currentLogin = "testLogin";
        final String newLogin = "newTestLogin";
        final String email = "test@test.ru";
        final String password = "testpswd";
        final String role = "admin";

        userGenerator(currentLogin, email, password, role);

        User expectedUser = new UserImpl();
        expectedUser.setId(1);
        expectedUser.setLogin(newLogin);
        expectedUser.setEmail(email);
        expectedUser.setPassword(password);
        expectedUser.setRole(role);

        User actualUser = userDAO.changeLogin(currentLogin, newLogin, password);

        System.out.println("ChangeLogin test:");
        assertEquals(expectedUser, actualUser);
        System.out.println("Expected: " + expectedUser);
        System.out.println("Actual: " + actualUser);
    }

    @Test
    public void changeEmail() {
        final String login = "testLogin";
        final String currentEmail = "test@test.ru";
        final String newEmail = "newTest@test.ru";
        final String password = "testpswd";
        final String role = "admin";

        userGenerator(login, currentEmail, password, role);

        User expectedUser = new UserImpl();
        expectedUser.setId(1);
        expectedUser.setLogin(login);
        expectedUser.setEmail(newEmail);
        expectedUser.setPassword(password);
        expectedUser.setRole(role);

        User actualUser = userDAO.changeEmail(currentEmail, newEmail, password);

        System.out.println("ChangeEmail test:");
        assertEquals(expectedUser, actualUser);
        System.out.println("Expected: " + expectedUser);
        System.out.println("Actual: " + actualUser);
    }

    @Test
    public void changePassword() {
        final String login = "testLogin";
        final String email = "test@test.ru";
        final String currentPassword = "testpswd";
        final String newPassword = "newTestPswd";
        final String role = "admin";

        userGenerator(login, email, currentPassword, role);

        User expectedUser = new UserImpl();
        expectedUser.setId(1);
        expectedUser.setLogin(login);
        expectedUser.setEmail(email);
        expectedUser.setPassword(newPassword);
        expectedUser.setRole(role);

        User actualUser = userDAO.changePassword(login, email, currentPassword, newPassword);

        System.out.println("ChangePassword test:");
        assertEquals(expectedUser, actualUser);
        System.out.println("Expected: " + expectedUser);
        System.out.println("Actual: " + actualUser);
    }

    private void userGenerator(String login, String email, String password, String role) {
        final String query = "insert into user (login, email, password, role)\n" +
                "values (?, ?, ?, ?);";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, role);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}