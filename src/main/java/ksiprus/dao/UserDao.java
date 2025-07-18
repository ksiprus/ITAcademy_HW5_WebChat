package ksiprus.dao;

import ksiprus.model.User;
import ksiprus.utils.DataSourceSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public void save(User user) throws SQLException {
        String sql = "INSERT INTO webchat.users (login, password, name, birth_date, reg_date, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataSourceSingleton.getInstance().getConnection();
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

    public User findByLogin(String login) throws SQLException {
        String sql = "SELECT * FROM webchat.users WHERE login = ?";
        try (Connection conn = DataSourceSingleton.getInstance().getConnection();
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
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM webchat.users";
        try (Connection conn = DataSourceSingleton.getInstance().getConnection();
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

    // Метод для подсчета пользователей
    public int count() throws SQLException {
        String sql = "SELECT COUNT(*) FROM webchat.users";
        try (Connection conn = DataSourceSingleton.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
}
