package ksiprus.service;

import ksiprus.core.ContextFactory;
import ksiprus.core.dto.Message;
import ksiprus.core.dto.User;
import ksiprus.service.api.IMessageService;
import ksiprus.storage.MessageStorageSQL;
import ksiprus.storage.UserStorageSQL;
import ksiprus.storage.api.IMessageStorage;
import ksiprus.storage.api.IUserStorage;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static ksiprus.storage.api.IMessageStorage.countMessages;

public class MessageService implements IMessageService {

    private final IMessageStorage messageRepo = ContextFactory.getBean("messageStorageSQL", MessageStorageSQL.class);
    private final IUserStorage IUserStorage = ContextFactory.getBean("userStorageSQL", UserStorageSQL.class);

    // Отправить сообщение от пользователя к другому пользователю по логинам
    @Override
    public void sendMessage(String fromLogin, String toLogin, String text) throws Exception {
        // Найти отправителя
        User sender = IUserStorage.findByLogin(fromLogin);
        // Найти получателя
        User receiver = IUserStorage.findByLogin(toLogin);

        if (sender == null) {
            throw new Exception("Отправитель не найден!");
        }
        if (receiver == null) {
            throw new Exception("Получатель не найден!");
        }
        if (text == null || text.trim().isEmpty()) {
            throw new Exception("Текст сообщения не может быть пустым!");
        }

        Message message = new Message.Builder()
                .senderId(sender.getUserId())
                .receiverId(receiver.getUserId())
                .text(text)
                .sentAt(LocalDateTime.now())
                .build();

        messageRepo.saveMessage(message);
    }

    // Получить список сообщений для пользователя
    @Override
    public List<Message> getMessagesFor(int userId) throws SQLException {
        return messageRepo.findByReceiverId(userId);
    }

    // Получить общее количество сообщений (для статистики)
    @Override
    public int getMessageCount() throws SQLException {
        return countMessages();
    }
}