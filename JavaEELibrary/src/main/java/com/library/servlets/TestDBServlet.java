package com.library.servlets;

import com.library.dao.impls.UserDAOImpl;
import com.library.dao.interfaces.UserDAO;
import com.library.interfaces.user.User;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestDBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAOImpl();
        User user1 = userDAO.addNewUser("login", "email@mail.ru", "password", "admin");
        User user2 = userDAO.addNewUser("login", "email@mail.ru", "password", "admin");
        if (user1 == null) resp.getWriter().println("user1 is equal to null");
        if (user2 == null) resp.getWriter().println("user2 is equal to null");

        System.out.println(req.getMethod());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(req.getMethod());
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("SERVLET: " + this.toString() + " is initialized!");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
        System.out.println("SERVLET: " + this.toString() + " is in service!");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("SERVLET: " + this.toString() + " is destroyed!");
    }
}
