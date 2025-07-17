package ksiprus.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ksiprus.model.User;
import ksiprus.service.UserService;

import java.io.IOException;

@WebServlet(urlPatterns = "api/login")
public class LoginServlet extends HttpServlet {
    private final UserService service = new UserService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            User user = service.login(login, password);
            req.getSession().setAttribute("user", user);
            if ("Admin".equals(user.getRole())) {
                resp.sendRedirect(req.getContextPath() + "ui/admin/statistics.jsp");

            } else {
                resp.sendRedirect(req.getContextPath() + "ui/user/message.jsp");
            }
        } catch (Exception e) {
            req.setAttribute("Ошибка авторизации!", e.getMessage());
            req.getRequestDispatcher("/ui/signIn.jsp").forward(req, resp);
        }
    }
}
