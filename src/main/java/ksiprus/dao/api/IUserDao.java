package ksiprus.dao.api;

import ksiprus.dto.User;

public interface IUserDao {
void save(User user);
void findByLogin(String login);
int count();
String existsByLogin(String login);
}
