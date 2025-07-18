package ksiprus.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet (urlPatterns = "/ui/admin/statistics")
public class StatisticsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("activeUsers", ksiprus.listener.ActiveUserSessionListener.getActiveUserCount());
        try {
            req.setAttribute("userCount", ksiprus.listener.ActiveUserSessionListener.getUserCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            req.setAttribute("messageCount", ksiprus.listener.ActiveUserSessionListener.getMessageCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("/ui/admin/statistics.jsp").forward(req, resp);
    }
}
