package com.library.impls.book;

import com.library.interfaces.book.Book;

public class BookImpl implements Book {
    private int id;
    private String name;
    private String authorName;
    private String resourcePath;
    private int pages;

    public BookImpl() {

    }

    public BookImpl(int id, String name, String authorName, String resourcePath, int pages) {
        this.id = id;
        this.name = name;
        this.authorName = authorName;
        this.resourcePath = resourcePath;
        this.pages = pages;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthorName() {
        return this.authorName;
    }

    @Override
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public int getPages() {
        return this.pages;
    }

    @Override
    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String getResourcePath() {
        return this.resourcePath;
    }

    @Override
    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public String toString() {
        return "Id: "  + this.id + " Name: " + this.name + " AuthorName: " + this.authorName
                + " Pages: " + this.pages + " Resource path: " + this.resourcePath;
    }

    @Override
    public boolean equals(Object book) {
        if (this == book)
            return true;

        if (book == null || book.getClass() != this.getClass())
            return false;

        Book book1 = (Book) book;
        return this.id == book1.getId()
                && this.pages == book1.getPages()
                && ((this.name == (book1.getName())) || (this.name != null) && (this.name.equals(book1.getName())))
                && ((this.authorName == (book1.getAuthorName())) || (this.authorName != null) && (this.authorName.equals(book1.getAuthorName())))
                && ((this.resourcePath == (book1.getResourcePath()) || (this.resourcePath != null) && (this.resourcePath.equals(book1.getResourcePath()))));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 0;
        result = id + (name == null ? 0 : name.hashCode());
        result = prime * result + (authorName == null ? 0 : authorName.hashCode());
        result = prime * result + (pages);
        result = prime * result + (authorName == null ? 0 : authorName.hashCode());

        return result;
    }
}
