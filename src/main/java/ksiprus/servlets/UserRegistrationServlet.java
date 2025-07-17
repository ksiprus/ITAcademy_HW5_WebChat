package ksiprus.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ksiprus.service.UserService;

import java.io.IOException;

@WebServlet(urlPatterns = "/api/user")
public class UserRegistrationServlet extends HttpServlet {
    private final UserService service = new UserService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String birthDate = req.getParameter("birthDate");

        try {
            service.register(login, password, name, birthDate);
            req.setAttribute("successMessage", "регистрация прошла успешно!");
            req.getRequestDispatcher("/ui/index.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error message", "Ошибка!" + e.getMessage());
            req.getRequestDispatcher("/ui/signUp.jsp").forward(req, resp);

        }
    }
}