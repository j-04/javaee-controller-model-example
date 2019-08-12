package com.library.interfaces.user;

public interface User {
    int getId();
    String getLogin();
    String getEmail();
    String getRole();
    String getPassword();
    void setId(int id);
    void setLogin(String login);
    void setEmail(String email);
    void setRole(String role);
    void setPassword(String password);
    String toString();
    boolean equals(Object obj);
}
