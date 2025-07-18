package ksiprus.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ksiprus.dao.UserDao;
import ksiprus.service.UserService;

import java.io.IOException;

@WebServlet(urlPatterns = "/ui/user")
public class UserRegistrationServlet extends HttpServlet {
    private final UserService service = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String birthDate = req.getParameter("birthDate");

        try {
            UserDao userDao = new UserDao();

            // Проверка, существует ли такой логин
            if (userDao.existsByLogin(login)) {
                req.setAttribute("error", "Пользователь с таким логином уже существует!");
                req.getRequestDispatcher("/ui/register.jsp").forward(req, resp);
                return;
            }

            service.register(login, password, name, birthDate);
            req.setAttribute("successMessage", "Регистрация прошла успешно!");
            req.getRequestDispatcher("/ui/index.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "Ошибка регистрации: " + e.getMessage());
            req.getRequestDispatcher("/ui/error.jsp").forward(req, resp);
        }
    }
}
