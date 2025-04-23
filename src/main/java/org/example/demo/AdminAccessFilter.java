package org.example.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/ListUserServlet", "/adminDashboard.jsp", "/UserSmsServlet","/UpdateSmsServlet","/edit.jsp"})
public class AdminAccessFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false); // don't create if not existing

        String userType = (session != null) ? (String) session.getAttribute("type") : null;

        if (userType != null && userType.equals("admin")) {
            chain.doFilter(request, response); // allow request
        } else {
            res.sendRedirect("login.jsp?error=Unauthorized+access");
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {}
    public void destroy() {}
}
