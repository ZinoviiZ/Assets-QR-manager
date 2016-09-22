package sand_box.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sand_box.repositories.UserRepository;
import sand_box.services.interfaces.UserService;
import sand_box.tables.login.User;

import java.util.List;

/**
 * Created by Zinoviy on 9/5/16.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User getUser(String login) {
        User user = userRepository.getUsetByLogin(login);
        return user;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(long[] allid) {
        for(long id : allid)
        userRepository.delete(id);
    }
}
