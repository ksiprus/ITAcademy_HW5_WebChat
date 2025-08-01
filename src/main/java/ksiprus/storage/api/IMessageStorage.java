package ksiprus.storage.api;

import ksiprus.core.dto.Message;
import ksiprus.utils.DataSourceProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface IMessageStorage {
    // Получить общее количество сообщений
    static int countMessages() throws SQLException {
        String sql = "SELECT COUNT(*) FROM webchat.messages";
        try (Connection conn = DataSourceProvider.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
            return 0;
        }
    }

    // Сохранить сообщение в базе данных
    void saveMessage(Message message) throws SQLException;

    // Получить все сообщения, отправленные данному пользователю
    List<Message> findByReceiverId(int userId) throws SQLException;
}
