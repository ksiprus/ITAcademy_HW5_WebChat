package ksiprus.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/go/*", "/"})
public class NavigationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getPathInfo();
        String jspPath;

        if (page == null || page.equals("/") || page.isEmpty()) {

            resp.sendRedirect(req.getContextPath().concat("/go/index"));
            return;
        }

        switch (page) {
            case "/index":
                jspPath = "/WEB-INF/ui/index.jsp";
                break;
            case "/signUp":
                jspPath = "/WEB-INF/ui/signUp.jsp";
                break;
            case "/signIn":
                jspPath = "/WEB-INF/ui/signIn.jsp";
                break;
            case "/user/message":
                jspPath = "/WEB-INF/ui/user/message.jsp";
                break;
            case "/user/chats":
                jspPath = "/api/user/message";
                break;
            case "/admin/statistics":
                jspPath = "/api/admin/statistics";
                break;
            default:
                jspPath = "/WEB-INF/ui/index.jsp";
                req.setAttribute("error", "страница не найдена!");
        }
        req.getRequestDispatcher(jspPath).forward(req, resp);

    }
}
