package by.bsu.kolodyuk.service;

import by.bsu.kolodyuk.model.User;
import by.bsu.kolodyuk.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    public User getUser(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    public boolean addUser(User user) {
        User existing = userRepository.findByLogin(user.getLogin());
        if(existing == null) {
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

}
