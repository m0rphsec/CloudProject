package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Connection connection = DatabaseConnection.initializeDatabase();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
            ResultSet resultSet = statement.executeQuery(query);

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            if (resultSet.next()) {
                out.println("<h3>Welcome, " + username + "!</h3>");
            } else {
                out.println("<h3>Invalid credentials</h3>");
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
