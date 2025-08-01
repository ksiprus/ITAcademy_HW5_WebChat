package ksiprus.service.listener;


import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.*;
import ksiprus.storage.api.IUserStorage;
import ksiprus.storage.api.IMessageStorage;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@WebListener
public class ActiveUserSessionListener implements HttpSessionAttributeListener, HttpSessionListener {
    private static final Set<HttpSession> sessions = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if ("user".equals(event.getName())) {
            sessions.add(event.getSession());
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if ("user".equals(event.getName())) {
            sessions.add(event.getSession());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if ("user".equals(event.getName())) {
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


    public static int getUserCount() throws SQLException {
        return IUserStorage.count();
    }


    public static int getMessageCount() throws SQLException {
        return IMessageStorage.countMessages();
    }
}
