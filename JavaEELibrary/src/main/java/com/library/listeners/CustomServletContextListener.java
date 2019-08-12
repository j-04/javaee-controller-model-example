package com.library.listeners;

import com.library.db.ConnectionDB;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName(ConnectionDB.JDBC_DRIVER);
            System.out.println("Connecting to database...");
            ConnectionDB.connection = DriverManager.getConnection(ConnectionDB.DB_URL, ConnectionDB.DB_LOGIN, ConnectionDB.DB_PASSWORD);
            System.out.println("Creating statement...");
            ConnectionDB.statement = ConnectionDB.connection.createStatement();
            System.out.println("Connection successful!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
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
}
