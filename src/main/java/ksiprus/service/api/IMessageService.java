package ksiprus.service.api;

import ksiprus.core.dto.Message;
import ksiprus.service.exception.MessageServiceException;

import java.sql.SQLException;
import java.util.List;

public interface IMessageService {


    void sendMessage(String fromLogin, String toLogin, String text) throws MessageServiceException, Exception;

    List<Message> getMessagesFor(int userId) throws MessageServiceException, SQLException;

    int getMessageCount() throws MessageServiceException, SQLException;


}
