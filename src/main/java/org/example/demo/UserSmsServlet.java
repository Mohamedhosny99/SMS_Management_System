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
import model.SmsRecord;

@WebServlet("/UserSmsServlet")
public class UserSmsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String type = (session != null) ? (String) session.getAttribute("type") : null;

        if (type == null || !"admin".equals(type)) {
            response.sendRedirect("login.jsp?error=Unauthorized access");
            return;
        }

        String userId = request.getParameter("user_id");
        if (userId == null && session != null) {
            Object idObj = session.getAttribute("userId");
            if (idObj != null) userId = idObj.toString();
        }

        List<SmsRecord> smsList = new ArrayList<>();

        if (userId != null && !userId.isEmpty()) {
            try (Connection conn = DBconnection.getConnection()) {
                String sql = "SELECT sms_id, user_id, to_number, from_number, body, sent_date, inbound, status FROM sms WHERE user_id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(userId));
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    SmsRecord sms = new SmsRecord();
                    sms.setSmsId(rs.getInt("sms_id"));
                    sms.setUserId(rs.getInt("user_id"));
                    sms.setTo(rs.getString("to_number"));
                    sms.setFrom(rs.getString("from_number"));
                    sms.setBody(rs.getString("body"));
                    sms.setDate(rs.getDate("sent_date").toString());
                    sms.setInbound(rs.getBoolean("inbound"));
                    sms.setStatus(rs.getString("status") != null ? rs.getString("status") : "N/A");
                    smsList.add(sms);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("smsList", smsList);
        request.setAttribute("userId", userId);
        request.getRequestDispatcher("list-user-sms.jsp").forward(request, response);
    }
}
