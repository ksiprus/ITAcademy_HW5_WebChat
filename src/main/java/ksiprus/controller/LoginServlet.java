package ksiprus.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ksiprus.core.dto.User;
import ksiprus.service.UserService;

import java.io.IOException;

@WebServlet(urlPatterns = "/api/login")
public class LoginServlet extends HttpServlet {
    private final UserService service = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            User user = service.login(login, password);

            // Проверка на наличие пользователя и корректность пароля
            if (user == null || password == null) {
                req.setAttribute("loginError", "Неверный логин или пароль.");
                req.getRequestDispatcher("/WEB-INF/ui/LoginError.jsp").forward(req, resp);
                return;
            }

            req.getSession().setAttribute("user", user);

            if ("Admin".equals(user.getRole())) {
                resp.sendRedirect(req.getContextPath().concat("/go/admin/statistics"));
            } else {
                resp.sendRedirect(req.getContextPath().concat("/go/user/message"));
            }
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath().concat("/go/signIn"));
        }
    }
}
