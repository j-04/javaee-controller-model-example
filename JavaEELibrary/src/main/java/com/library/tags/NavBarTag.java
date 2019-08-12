package com.library.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class NavBarTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        String navBar = "<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n" +
                "  <a class=\"navbar-brand\" href=\"#\">Library</a>\n" +
                "  <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\"\n" +
                "          aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                "    <span class=\"navbar-toggler-icon\"></span>\n" +
                "  </button>\n" +
                "  <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n" +
                "    <ul class=\"navbar-nav\">\n" +
                "      <li class=\"nav-item active\">\n" +
                "        <a class=\"nav-link\" href=\"/\">Home<span class=\"sr-only\">(current)</span></a>\n" +
                "      </li>\n" +
                "      <li class=\"nav-item\">\n" +
                "        <a class=\"nav-link\" href=\"#\">Watch books</a>\n" +
                "      </li>\n" +
                "      <li class=\"nav-item\">\n" +
                "        <a class=\"nav-link\" href=\"#\">Find books by author</a>\n" +
                "      </li>\n" +
                "      <li class=\"nav-item\">\n" +
                "        <a class=\"nav-link\" href=\"#\">Registration of new book</a>\n" +
                "      </li>\n" +
                "      <li class=\"nav-item\">\n" +
                "        <a class=\"nav-link\" href=\"/registration\">Sign up</a>\n" +
                "      </li>\n" +
                "      <li class=\"nav-item\">\n" +
                "        <a class=\"nav-link\" href=\"/authorisation\">Sign in</a>\n" +
                "      </li>\n" +
                "      <li class=\"nav-item\">\n" +
                "        <a class=\"nav-link\" href=\"#\">Delete account</a>\n" +
                "      </li>\n" +
                "      <li class=\"nav-item\">\n" +
                "        <a class=\"nav-link\" href=\"#\">Delete user by login</a>\n" +
                "      </li>\n" +
                "      <li class=\"nav-item\">\n" +
                "        <a class=\"nav-link\" href=\"#\">View all issued books and their users.</a>\n" +
                "      </li>\n" +
                "    </ul>\n" +
                "  </div>\n" +
                "</nav>";
        JspWriter jspWriter = getJspContext().getOut();
        jspWriter.println(navBar);
    }
}
