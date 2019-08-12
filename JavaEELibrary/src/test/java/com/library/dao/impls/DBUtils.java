package com.library.dao.impls;

import com.library.db.ConnectionDB;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.library.db.ConnectionDB.connection;
import static com.library.db.ConnectionDB.statement;

public class DBUtils {

    public static void setUpConnection() {
        try {
            Class.forName(ConnectionDB.JDBC_DRIVER);
            System.out.println("Connecting to database...");
            ConnectionDB.connection = DriverManager.getConnection(ConnectionDB.DB_URL, ConnectionDB.DB_LOGIN, ConnectionDB.DB_PASSWORD);
            System.out.println("Creating statement...");
            ConnectionDB.statement = ConnectionDB.connection.createStatement();
            System.out.println("Connection successful!");
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found!");
        } catch (SQLException e) {
            System.err.println("Connection failed!");
        }
    }

    public static void closeConnections() {
        try {
            System.out.println("Closing connections...");
            System.out.println("Closing statement...");
            ConnectionDB.statement.close();
            System.out.println("Closing database connection...");
            ConnectionDB.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateBookTable() {
        final String dropQuery = "drop table book";
        final String createQuery = "create table if not exists book\n" +
                "(\n" +
                "    id INT auto_increment,\n" +
                "    name varchar(255) not null unique,\n" +
                "    authorName varchar(255) not null,\n" +
                "    pages int not null,\n" +
                "    resourcePath varchar(255) not null,\n" +
                "    primary key (id)\n" +
                ") engine = innodb;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(dropQuery);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(createQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void updateIssuedBookTable() {
        final String query = "drop table is exists issuedbook;";
        final String query2 = "create table if not exists issuedbook\n" +
                "(\n" +
                "    id int auto_increment,\n" +
                "    userid int not null,\n" +
                "    bookid int not null,\n" +
                "    primary key (id),\n" +
                "    unique (userid, bookid),\n" +
                "\n" +
                "    constraint userid\n" +
                "    foreign key userid(userid)\n" +
                "    references user(id)\n" +
                "    ON DELETE CASCADE\n" +
                "    ON UPDATE CASCADE,\n" +
                "\n" +
                "    CONSTRAINT bookid\n" +
                "    foreign key bookid(bookid)\n" +
                "    references book(id)\n" +
                "    ON DELETE CASCADE\n" +
                "    ON UPDATE CASCADE\n" +
                ") engine = innodb;";
        try {
            statement.executeUpdate(query);
            statement.executeUpdate(query2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUserTable() {
        final String dropQuery = "drop table user;";
        final String createQuery = "create table if not exists user\n" +
                "(\n" +
                "    id int auto_increment,\n" +
                "    login varchar(255) not null unique,\n" +
                "    email varchar(255) not null unique,\n" +
                "    password varchar(255) not null,\n" +
                "    role varchar(6) not null,\n" +
                "    primary key (id)\n" +
                ") engine = innodb;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(dropQuery);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(createQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
