package ksiprus.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ksiprus.core.dto.User;

import java.io.IOException;

@WebFilter(urlPatterns = {"/ui/admin/*"})
public class AdminSecurityFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null && "Admin".equals(user.getRole())) {
            chain.doFilter(request, response);
        } else {
           request.getRequestDispatcher("/ui/signIn.jsp").forward(request, response);
        }
    }
}
