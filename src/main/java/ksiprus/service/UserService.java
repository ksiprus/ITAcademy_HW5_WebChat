package ksiprus.service;

import ksiprus.dao.UserDao;
import ksiprus.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserService {
    private final UserDao userDao = new UserDao();

    public void register(String login, String password, String name, String birthDate) throws Exception {
        if (userDao.findByLogin(login) != null) {
            throw new Exception("Логин уже существует!");
        }

        User user = new User.Builder()
                .login(login)
                .password(password)
                .name(name)
                .birthDate(LocalDate.parse(birthDate))
                .regDate(LocalDateTime.now())
                .role("User")
                .build();
        userDao.save(user);

    }

    public User login(String login, String password) throws Exception {
        User user = userDao.findByLogin(login);
        if (user == null || !user.getPassword().equals(password)){
            throw new Exception("Неверный логин или пароль!");


        }
        return user;
    }
}
