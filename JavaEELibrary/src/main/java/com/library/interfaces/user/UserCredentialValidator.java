package com.library.interfaces.user;

public interface UserCredentialValidator {
    boolean isUserLoginValid(String login);
    boolean isUserEmailValid(String email);
    boolean isUserPasswordValid(String password);
}
