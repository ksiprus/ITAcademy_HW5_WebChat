package ksiprus.service;

import ksiprus.dto.UserDto;
import ksiprus.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserService {
    private final UserDto userDto = new UserDto();

    public void register(String login, String password, String name, String birthDate) throws Exception {
        if (userDto.findByLogin(login) != null) {
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
        userDto.save(user);

    }

    public User login(String login, String password) throws Exception {
        User user = userDto.findByLogin(login);
        if (user == null || !user.getPassword().equals(password)){
            throw new Exception("Неверный логин или пароль!");


        }
        return user;
    }
}
