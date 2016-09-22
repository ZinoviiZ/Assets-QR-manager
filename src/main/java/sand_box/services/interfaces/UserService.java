package sand_box.services.interfaces;

import sand_box.tables.login.User;

import java.util.List;

/**
 * Created by Zinoviy on 9/5/16.
 */
public interface UserService {
    public List<User> getUsers();

    public User getUser(String login);

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(long[] id);
}
