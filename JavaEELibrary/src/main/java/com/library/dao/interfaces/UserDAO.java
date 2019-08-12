package com.library.dao.interfaces;

import com.library.interfaces.user.User;

import java.util.List;

public interface UserDAO {
    User addNewUser(String login, String email, String password, String role);

    List<User> getUsers();
    User getById(int id);
    User getByLogin(String login);
    User getByEmail(String email);
    User getByCredentials(String login, String password);

    int deleteById(int id);
    int deleteByLogin(String login);
    int deleteByEmail(String email);

    int deleteByLogin(String login, String password);
    int deleteByEmail(String email, String password);

    User changeLogin(String currentLogin, String newLogin, String password);
    User changeEmail(String currentEmail, String newEmail, String password);
    User changePassword(String login, String email, String currentPassword, String newPassword);
}
