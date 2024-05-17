package org.HRP.service;

import org.HRP.model.User;
import org.HRP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateEmail(String name, String oldEmail, String newEmail) {
        List<User> userList = userRepository.findAllByNameAndEmail(name, oldEmail);
        if(!userList.isEmpty()) {
            User user = userList.get(0);
            user.setEmail(newEmail);
            userRepository.save(user);
        }
    }

    public void removeUser(String name, String email) {
        List<User> userList = userRepository.findAllByNameAndEmail(name, email);
        if(!userList.isEmpty()) {
            User user = userList.get(0);
            userRepository.delete(user);
        }
    }

    public boolean userExists(User user) {
        return !userRepository.findAllByNameAndEmail(user.getName(), user.getEmail()).isEmpty();
    }
}
