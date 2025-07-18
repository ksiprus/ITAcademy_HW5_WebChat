package ksiprus.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ksiprus.model.User;
import ksiprus.service.UserService;

import java.io.IOException;

@WebServlet(urlPatterns = "/ui/login")
public class LoginServlet extends HttpServlet {
    private final UserService service = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            User user = service.login(login, password);
            req.getSession().setAttribute("user", user);

            if ("Admin".equals(user.getRole())) {
                req.getRequestDispatcher("/ui/admin/statistics.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/ui/user/message.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Ошибка авторизации: " + e.getMessage());
            req.getRequestDispatcher("/ui/signIn.jsp").forward(req, resp);
        }
    }
}
