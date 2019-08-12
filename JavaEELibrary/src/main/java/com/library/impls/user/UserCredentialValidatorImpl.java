package com.library.impls.user;

import com.library.interfaces.user.UserCredentialValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserCredentialValidatorImpl implements UserCredentialValidator {
    @Override
    public boolean isUserLoginValid(String login) {
        final String regex = "^[a-zA-Z0-9_-]{1,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    @Override
    public boolean isUserEmailValid(String email) {
        final String regex = "^([a-z0-9_.-]+)@([a-z0-9_.-]+).([a-z.]{2,6})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean isUserPasswordValid(String password) {
        final String regex = "^[a-z0-9_-]{5,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
