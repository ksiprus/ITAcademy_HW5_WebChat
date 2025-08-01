package ksiprus.service;

import ksiprus.core.ContextFactory;
import ksiprus.core.dto.User;
import ksiprus.service.api.IUserService;
import ksiprus.service.exception.UserServiceException;
import ksiprus.storage.UserStorageSQL;
import ksiprus.storage.api.IUserStorage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserService implements IUserService {
    private final IUserStorage IUserStorage =
            ContextFactory.getBean("userStorageSQL", UserStorageSQL.class);

    @Override
    public void register(String login, String password, String name, String birthDate) throws Exception {
        if (IUserStorage.findByLogin(login) != null) {
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
        IUserStorage.save(user);

    }

    @Override
    public User login(String login, String password) throws UserServiceException {
        User user = null;
        try {
            user = IUserStorage.findByLogin(login);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (user == null || !user.getPassword().equals(password)) {
            try {
                throw new Exception("Неверный логин или пароль!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
        return user;
    }
}
