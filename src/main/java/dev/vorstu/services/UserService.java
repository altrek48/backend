package dev.vorstu.services;

import dev.vorstu.entities.Role;
import dev.vorstu.entities.User;
import dev.vorstu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean saveUser(User user) {
        try {
            user.setRole(Role.STUDENT);
            user.setEnable(true);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkAvailableUsername(String username) {
        if(userRepository.checkAvailableUsername(username)) {
            return true;
        }
        else return false;
    }

}
