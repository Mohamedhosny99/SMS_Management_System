package org.example.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBConnection.DBconnection;
import model.User;

@WebServlet("/ListUserServlet")
public class ListUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        String username = null;
        String userID = null;
        HttpSession session = request.getSession();
        String userSQL = "SELECT user_id, username FROM customer WHERE 1=1";
        
        try {
            conn = DBconnection.getConnection();

            // Check for username parameter
            username = request.getParameter("username");
            if (username != null && !username.trim().isEmpty()) {
                userSQL += " AND username LIKE ?";
                System.out.println("Username parameter: " + username);
            } else {
                username = null; // Ensure it's null if empty or not present
            }

            // Check for userID parameter
            userID = request.getParameter("userID");
            if (userID != null && !userID.trim().isEmpty()) {
                userSQL += " AND user_id = ?";
                System.out.println("UserID parameter: " + userID);
            } else {
                userID = null; // Ensure it's null if empty or not present
            }

            System.out.println("Final SQL: " + userSQL);
            pstmt = conn.prepareStatement(userSQL);

            int paramIndex = 1;
            
            // Set username parameter
            if (username != null) {
                pstmt.setString(paramIndex++, "%" + username + "%");
                System.out.println("Set username parameter at position " + (paramIndex-1));
            }
            
            // Set userID parameter
            if (userID != null) {
                pstmt.setInt(paramIndex++, Integer.parseInt(userID));
                System.out.println("Set userID parameter at position " + (paramIndex-1));
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                userList.add(new User(rs.getInt("user_id"), rs.getString("username")));
                System.out.println("Added user to list");
            }
            
            System.out.println("Total users found: " + userList.size());
            request.setAttribute("userList", userList);
            request.getRequestDispatcher("list-user.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}