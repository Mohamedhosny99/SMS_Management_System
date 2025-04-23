package org.example.demo;
import DBConnection.DBconnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userIdStr = request.getParameter("user_id");

        if (userIdStr != null && !userIdStr.isEmpty()) {
            int userId = Integer.parseInt(userIdStr);

            try (Connection conn = DBconnection.getConnection()) {
                String sql = "DELETE FROM users WHERE user_id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, userId);
                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("User deleted successfully: ID = " + userId);
                    } else {
                        System.out.println("No user found with ID = " + userId);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Database error occurred.");
            }
        }

        response.sendRedirect("ListUserServlet");
    }
}
