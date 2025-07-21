package ksiprus.service;

import ksiprus.dto.MessageDto;
import ksiprus.dto.UserDto;
import ksiprus.model.Message;
import ksiprus.model.User;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class MessageService {

    private final MessageDto messageRepo = new MessageDto();
    private final UserDto userDto = new UserDto();

    // Отправить сообщение от пользователя к другому пользователю по логинам
    public void sendMessage(String fromLogin, String toLogin, String text) throws Exception {
        // Найти отправителя
        User sender = userDto.findByLogin(fromLogin);
        // Найти получателя
        User receiver = userDto.findByLogin(toLogin);

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
    public List<Message> getMessagesFor(int userId) throws SQLException {
        return messageRepo.findByReceiverId(userId);
    }

    // Получить общее количество сообщений (для статистики)
    public int getMessageCount() throws SQLException {
        return messageRepo.countMessages();
    }
}