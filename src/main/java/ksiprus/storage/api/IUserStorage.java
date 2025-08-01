package ksiprus.storage.api;

import ksiprus.core.dto.User;
import ksiprus.utils.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IUserStorage {
    // Метод для подсчета пользователей
    static int count() throws SQLException {
        String sql = "SELECT COUNT(*) FROM webchat.users";
        try (Connection conn = DataSourceProvider.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    void save(User user) throws SQLException;

    User findByLogin(String login) throws SQLException;

    // Метод для получения всех пользователей
    List<User> findAll() throws SQLException;

    // Проверка, существует ли пользователь с данным логином
    boolean existsByLogin(String login) throws SQLException;
}
