package com.library.impls.user;

import com.library.interfaces.user.User;

public class UserImpl implements User {
    private int id;
    private String login;
    private String email;
    private String password;
    private String role;

    public UserImpl() {

    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getLogin() {
        return this.login;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getRole() {
        return this.role;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Login: " + login + " Email: " + email + " Password: " + password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        User user = (User) obj;
        return this.id == user.getId()
                && (this.login == (user.getLogin()) || ((this.login != null) && (this.login.equals(user.getLogin()))))
                && (this.email == (user.getEmail()) || ((this.email != null) && (this.email.equals(user.getEmail()))))
                && (this.password == (user.getPassword()) || ((this.password != null) && this.password.equals(user.getPassword())))
                && (this.role == (user.getRole()) || ((this.role!= null) && this.role.equals(user.getRole())));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 0;
        result = id + (login == null ? 0 : login.hashCode());
        result = prime * result + (email == null ? 0 : email.hashCode());

        return result;
    }
}
