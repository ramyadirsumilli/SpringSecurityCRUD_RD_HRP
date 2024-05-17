package org.HRP.controller;

import org.HRP.model.User;
import org.HRP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/usersList", method = RequestMethod.GET)
    public List<User> allUsers() {
        return userService.allUsers();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestParam("name") String name, @RequestParam("email") String email) {
        User user = new User(name, email);
        if (userService.userExists(user)) {
            return "User already exists!";
        }
        userService.createUser(user);
        return "User Added";
    }

    @RequestMapping(value = "/changeEmail", method = RequestMethod.PUT)
    public String updateEmail(@RequestParam("name") String name, @RequestParam("oldEmail") String oldEmail,
                              @RequestParam("newEmail") String newEmail) {
        userService.updateEmail(name, oldEmail, newEmail);
        return "user updated!";
    }

    @RequestMapping(value="/removeUser", method = RequestMethod.DELETE)
    public String removeUser(@PathVariable("name") String name, @RequestParam("email") String email) {
        userService.removeUser(name, email);
        return "User Removed";
    }


}
