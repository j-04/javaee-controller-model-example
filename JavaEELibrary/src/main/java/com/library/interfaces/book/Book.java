package com.library.interfaces.book;

public interface Book {
     int getId();
     void setId(int id);
     String getName();
     void setName(String name);
     String getAuthorName();
     void setAuthorName(String authorName);
     int getPages();
     void setPages(int pages);
     String getResourcePath();
     void setResourcePath(String resourcePath);
     String toString();
     boolean equals(Object obj);
}
