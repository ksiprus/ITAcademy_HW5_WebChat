package ksiprus.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebListener
public class ActiveUserSessionListener implements HttpSessionAttributeListener, HttpSessionListener {
    private static final Set<HttpSession> sessions = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (event.getName().equals("user")) {
            sessions.add(event.getSession());
        }
    }
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if (event.getName().equals("user")) {
            sessions.remove(event.getSession());
        }
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        sessions.remove(event.getSession());
    }
    public static int getActiveUserCount() {
        return sessions.size();
    }
}
