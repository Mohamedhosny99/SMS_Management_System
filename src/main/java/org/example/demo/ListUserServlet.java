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
        HttpSession session = request.getSession(false);
        String type = (session != null) ? (String) session.getAttribute("type") : null;

        if (type == null || !"admin".equals(type)) {
            response.sendRedirect("login.jsp?error=Unauthorized access");
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        String username = request.getParameter("username");
        String userID = request.getParameter("userID");

        String userSQL = "SELECT user_id, username FROM customer WHERE 1=1";

        try {
            conn = DBconnection.getConnection();

            if (username != null && !username.trim().isEmpty()) {
                userSQL += " AND username LIKE ?";
            } else {
                username = null;
            }

            if (userID != null && !userID.trim().isEmpty()) {
                userSQL += " AND user_id = ?";
            } else {
                userID = null;
            }

            pstmt = conn.prepareStatement(userSQL);
            int paramIndex = 1;

            if (username != null) {
                pstmt.setString(paramIndex++, "%" + username + "%");
            }

            if (userID != null) {
                pstmt.setInt(paramIndex++, Integer.parseInt(userID));
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                userList.add(new User(rs.getInt("user_id"), rs.getString("username")));
            }

            request.setAttribute("userList", userList);
            request.getRequestDispatcher("list-user.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); if (pstmt != null) pstmt.close(); if (conn != null) conn.close(); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}
