package ksiprus.storage;

import ksiprus.core.dto.Message;
import ksiprus.storage.api.IMessageStorage;
import ksiprus.utils.DataSourceProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageStorageSQL implements IMessageStorage {

    // Сохранить сообщение в базе данных
    @Override
    public void saveMessage(Message message) throws SQLException {
        String sql = "INSERT INTO webchat.messages (sender_id, receiver_id, text, sent_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = DataSourceProvider.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, message.getSenderId());
            pstmt.setInt(2, message.getReceiverId());
            pstmt.setString(3, message.getText());
            pstmt.setTimestamp(4, Timestamp.valueOf(message.getSentAt()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Получить все сообщения, отправленные данному пользователю
    @Override
    public List<Message> findByReceiverId(int userId) throws SQLException {
        List<Message> msgs = new ArrayList<>();
        String sql = "SELECT * FROM webchat.messages WHERE receiver_id = ? ORDER BY sent_at DESC";

        try (Connection conn = DataSourceProvider.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                msgs.add(new Message.Builder()
                        .id(rs.getInt("id"))
                        .senderId(rs.getInt("sender_id"))
                        .receiverId(rs.getInt("receiver_id"))
                        .text(rs.getString("text"))
                        .sentAt(rs.getTimestamp("sent_at").toLocalDateTime())
                        .build());
            }
        }
        return msgs;
    }


}
