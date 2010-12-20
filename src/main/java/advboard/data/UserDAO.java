package advboard.data;

import java.util.Collection;
import java.util.List;

public interface UserDAO
{
    User getUserById(Long id);

    User getUserByName(String name);

    User getUserByNamePass(String name, String pass);

    List<User> getUsersByRole(Role role);

    List<User> getUsersByIds(Collection<Long> ids);

    void deleteUsersByIds(Collection<Long> ids);

    void deleteUser(User user);

    void addUser(User user);
}
