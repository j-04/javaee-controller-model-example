package com.library.db;

import java.sql.Connection;
import java.sql.Statement;

public class ConnectionDB {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/library?useUnicode=true&serverTimezone=UTC&useSSL=false";
    public static final String DB_LOGIN = "root";
    public static final String DB_PASSWORD = "root";
    public static Connection connection;
    public static Statement statement;
}
