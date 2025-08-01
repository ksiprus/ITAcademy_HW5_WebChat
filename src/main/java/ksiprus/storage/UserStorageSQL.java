package ksiprus.storage;

import ksiprus.core.dto.User;
import ksiprus.storage.api.IUserStorage;
import ksiprus.utils.DataSourceProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserStorageSQL implements IUserStorage {
    @Override
    public void save(User user) throws SQLException {
        String sql = "INSERT INTO webchat.users (login, password, name, birth_date, reg_date, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataSourceProvider.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setDate(4, Date.valueOf(user.getBirthDate()));
            ps.setTimestamp(5, Timestamp.valueOf(user.getRegDate()));
            ps.setString(6, user.getRole());
            ps.executeUpdate();
        }
    }

    @Override
    public User findByLogin(String login) throws SQLException {
        String sql = "SELECT * FROM webchat.users WHERE login = ?";
        try (Connection conn = DataSourceProvider.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User.Builder()
                        .id(rs.getInt("id"))
                        .login(rs.getString("login"))
                        .password(rs.getString("password"))
                        .name(rs.getString("name"))
                        .birthDate(rs.getDate("birth_date").toLocalDate())
                        .regDate(rs.getTimestamp("reg_date").toLocalDateTime())
                        .role(rs.getString("role"))
                        .build();
            }
            return null;
        }
    }

    // Метод для получения всех пользователей
    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM webchat.users";
        try (Connection conn = DataSourceProvider.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = new User.Builder()
                        .id(rs.getInt("id"))
                        .login(rs.getString("login"))
                        .password(rs.getString("password"))
                        .name(rs.getString("name"))
                        .birthDate(rs.getDate("birth_date").toLocalDate())
                        .regDate(rs.getTimestamp("reg_date").toLocalDateTime())
                        .role(rs.getString("role"))
                        .build();
                users.add(user);
            }
        }
        return users;
    }

    // Проверка, существует ли пользователь с данным логином
    @Override
    public boolean existsByLogin(String login) throws SQLException {
        String sql = "SELECT 1 FROM webchat.users WHERE login = ? LIMIT 1";
        try (Connection conn = DataSourceProvider.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

}
