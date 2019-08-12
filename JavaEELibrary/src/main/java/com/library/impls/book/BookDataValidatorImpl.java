package com.library.impls.book;

import com.library.interfaces.book.BookDataValidator;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookDataValidatorImpl implements BookDataValidator {
    @Override
    public boolean isBookNameValid(String name) {
        final String regex = "^[a-zA-Zа-яА-Я0-9 ]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    @Override
    public boolean isBookAuthorNameValid(String authorName) {
        final String regex = "^[a-zA-Zа-яА-Я ]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(authorName);
        return matcher.find();
    }

    @Override
    public boolean areBookPagesValid(String pages) {
        return StringUtils.isNumeric(pages);
    }
}
