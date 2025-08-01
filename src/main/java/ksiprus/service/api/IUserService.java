package ksiprus.service.api;

import ksiprus.core.dto.User;
import ksiprus.service.exception.UserServiceException;

public interface IUserService {
    void register(String login, String password, String name, String birthDate) throws UserServiceException, Exception;

    User login(String login, String password) throws UserServiceException;
}
