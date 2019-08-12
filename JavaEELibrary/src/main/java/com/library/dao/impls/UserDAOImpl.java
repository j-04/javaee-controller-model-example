package com.library.dao.impls;

import com.library.dao.interfaces.UserDAO;
import com.library.impls.user.UserImpl;
import com.library.interfaces.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.library.db.ConnectionDB.connection;

public class UserDAOImpl implements UserDAO {
    private static Logger log = Logger.getLogger(UserDAOImpl.class.getName());
    @Override
    public User addNewUser(String login, String email, String password, String role) {
        final String query = "insert into user (login, email, password, role)" +
                "values (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, role);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return null;
        }
        return this.getByLogin(login);
    }

    @Override
    public List<User> getUsers() {
        final String query = "select * from user;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(parserResultSet(resultSet));
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return null;
        }
        return users;
    }

    @Override
    public User getById(int id) {
        final String query = "select * from user where id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User resultUser = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            resultUser = parserResultSet(resultSet);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return null;
        }
        return resultUser;
    }

    @Override
    public User getByLogin(String login) {
        final String query = "select * from user where login = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User resultUser = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            resultUser = parserResultSet(resultSet);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return null;
        }
        return resultUser;
    }

    @Override
    public User getByEmail(String email) {
        final String query = "select * from user where email = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User resultUser = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            resultUser = parserResultSet(resultSet);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return null;
        }
        return resultUser;
    }

    @Override
    public User getByCredentials(String login, String password) {
        final String query = "select * from user where login = ? and password = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User resultUser = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            resultUser = parserResultSet(resultSet);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return null;
        }
        return resultUser;
    }

    @Override
    public int deleteById(int id) {
        final String query = "delete from user where id = ?";
        PreparedStatement preparedStatement = null;
        int result = -1;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return -1;
        }
        return result;
    }

    @Override
    public int deleteByLogin(String login) {
        final String query = "delete from user where login = ?";
        PreparedStatement preparedStatement = null;
        int result = -1;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return -1;
        }
        return result;
    }

    @Override
    public int deleteByEmail(String email) {
        final String query = "delete from user where email = ?";
        PreparedStatement preparedStatement = null;
        int result = -1;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return -1;
        }
        return result;
    }

    @Override
    public int deleteByLogin(String login, String password) {
        final String query = "delete from user where login = ? and password = ?";
        PreparedStatement preparedStatement = null;
        int result = -1;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return -1;
        }
        return result;
    }

    @Override
    public int deleteByEmail(String email, String password) {
        final String query = "delete from user where email = ? and password = ?";
        PreparedStatement preparedStatement = null;
        int result = -1;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return -1;
        }
        return result;
    }

    @Override
    public User changeLogin(String currentLogin, String newLogin, String password) {
        final String query = "update user set login = ? where login = ? and password = ?";
        PreparedStatement preparedStatement = null;
        User user = this.getByLogin(currentLogin);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newLogin);
            preparedStatement.setString(2, currentLogin);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return null;
        }
        user.setLogin(newLogin);
        return user;
    }

    @Override
    public User changeEmail(String currentEmail, String newEmail, String password) {
        final String query = "update user set email = ? where email = ? and password = ?";
        PreparedStatement preparedStatement = null;
        User user = this.getByEmail(currentEmail);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newEmail);
            preparedStatement.setString(2, currentEmail);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return null;
        }
        user.setEmail(newEmail);
        return user;
    }

    @Override
    public User changePassword(String login, String email, String currentPassword, String newPassword) {
        final String query = "update user set password = ? where login = ? and email = ? and password = ?";
        PreparedStatement preparedStatement = null;
        User user = this.getByLogin(login);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, currentPassword);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQLException: ", e);
            return null;
        }
        user.setPassword(newPassword);
        return user;
    }

    private User parserResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.getRow() != 0) {
            User user = new UserImpl();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));
            return user;
        }
        return null;
    }
}
