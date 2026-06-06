package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        out.println("<h2>Current Election Standings</h2>");
        out.println("<table border='1' cellpadding='5'><tr><th>ID</th><th>Candidate Name</th><th>Political Party</th><th>Total Votes</th></tr>");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting", "d2_96712_Devanand", "Devanand@96712");
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM candidates");
            
            // Loop through all candidate entries
            while(rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("party") + "</td>");
                out.println("<td>" + rs.getInt("votes") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            con.close();
        } catch (Exception e) {
            out.println("<h3 style='color:red;'>Failed to load results: " + e.getMessage() + "</h3>");
        }
        out.println("</body></html>");
    }
}
