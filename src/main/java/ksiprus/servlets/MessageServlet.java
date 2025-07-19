package ksiprus.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ksiprus.model.User;
import ksiprus.service.MessageService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/api/user/message")
public class MessageServlet extends HttpServlet {
    private final MessageService service = new MessageService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.setAttribute("error", "Пользователь не авторизован.");
            req.getRequestDispatcher("/WEB-INF/ui/error.jsp").forward(req, resp);
            return;
        }

        try {
            req.setAttribute("messages", service.getMessagesFor(user.getUserId()));
        } catch (SQLException e) {
            req.setAttribute("error", "Ошибка при получении сообщений: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/ui/error.jsp").forward(req, resp);
            return;
        }

        req.getRequestDispatcher("/WEB-INF/ui/user/chats.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.setAttribute("error", "Пользователь не авторизован.");
            req.getRequestDispatcher("/WEB-INF/ui/error.jsp").forward(req, resp);
            return;
        }

        String to = req.getParameter("to");
        String text = req.getParameter("text");

        if (to == null || to.isEmpty() || text == null || text.isEmpty()) {
            req.setAttribute("error", "Получатель и текст сообщения не могут быть пустыми.");
            req.getRequestDispatcher("/WEB-INF/ui/error.jsp").forward(req, resp);
            return;
        }

        try {
            service.sendMessage(user.getLogin(), to, text);
            // После отправки сообщения сразу показываем сообщения этим же способом
            req.setAttribute("messages", service.getMessagesFor(user.getUserId()));
        } catch (SQLException e) {
            req.setAttribute("error", "Ошибка при отправке сообщения: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/ui/error.jsp").forward(req, resp);
            return;
        } catch (Exception e) {
            req.setAttribute("error", "Неизвестная ошибка: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/ui/error.jsp").forward(req, resp);
            return;
        }

        req.getRequestDispatcher("/WEB-INF/ui/user/message.jsp").forward(req, resp);
    }
}
