package ksiprus.dto;

import ksiprus.model.Message;
import ksiprus.utils.DataSourceSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDto {

    // Сохранить сообщение в базе данных
    public void saveMessage(Message message) throws SQLException {
        String sql = "INSERT INTO webchat.messages (sender_id, receiver_id, text, sent_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = DataSourceSingleton.getInstance().getConnection();
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
    public List<Message> findByReceiverId(int userId) throws SQLException {
        List<Message> msgs = new ArrayList<>();
        String sql = "SELECT * FROM webchat.messages WHERE receiver_id = ? ORDER BY sent_at DESC";

        try (Connection conn = DataSourceSingleton.getInstance().getConnection();
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

    // Получить общее количество сообщений
    public static int countMessages() throws SQLException {
        String sql = "SELECT COUNT(*) FROM webchat.messages";
        try (Connection conn = DataSourceSingleton.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
            return 0;
        }
    }


}
