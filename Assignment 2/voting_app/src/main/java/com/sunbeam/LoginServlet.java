package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // Hardcoded admin validation check
        if(email.equals("devbudhawat1997@gmail.com") && password.equals("Pass@1997")) {
            // Redirect admin to the ResultServlet
            response.sendRedirect("ResultServlet");
        } else {
            out.println("<h3 style='color:red;'>Invalid Credentials or Not Authorized as Admin.</h3>");
        }
    }
}
