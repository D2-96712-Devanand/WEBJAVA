package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Retrieve parameters from form
        String name = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        try {
            // Load DB driver and establish connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting", "d2_96712_Devanand", "Devanand@96712");
            
            // Insert query execution
            String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            
            int status = ps.executeUpdate();
            if(status > 0) {
                out.println("<h3 style='color:green;'>Registration Successful! You can now log in.</h3>");
            } else {
                out.println("<h3 style='color:red;'>Registration failed. Please try again.</h3>");
            }
            con.close();
        } catch (Exception e) {
            out.println("<h3 style='color:red;'>Error occurred: " + e.getMessage() + "</h3>");
        }
    }
}
