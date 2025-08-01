package ksiprus.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet (urlPatterns = "/api/admin/statistics")
public class StatisticsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("activeUsers", ksiprus.service.listener.ActiveUserSessionListener.getActiveUserCount());
        try {
            req.setAttribute("userCount", ksiprus.service.listener.ActiveUserSessionListener.getUserCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            req.setAttribute("messageCount", ksiprus.service.listener.ActiveUserSessionListener.getMessageCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("/WEB-INF/ui/admin/statistics.jsp").forward(req, resp);
    }
}
