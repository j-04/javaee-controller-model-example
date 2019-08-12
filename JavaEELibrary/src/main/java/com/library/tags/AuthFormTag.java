package com.library.tags;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class AuthFormTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        JspContext jspContext = getJspContext();
        JspWriter jspWriter = jspContext.getOut();
        String authForm = "<form class=\"authform\">\n" +
                "        <div class=\"form-group\">\n" +
                "            <label for=\"login\">Login</label>\n" +
                "            <input type=\"text\" class=\"form-control\" id=\"login\" placeholder=\"Login\">\n" +
                "        </div>\n" +
                "        <div class=\"form-group\">\n" +
                "            <label for=\"password\">Password</label>\n" +
                "            <input type=\"password\" class=\"form-control\" id=\"password\" placeholder=\"Password\">\n" +
                "            <button type=\"submit\" class=\"btn btn-primary mt-3\">Submit</button>\n" +
                "    </form>";
        jspWriter.println(authForm);
    }
}
