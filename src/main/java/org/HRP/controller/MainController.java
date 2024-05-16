package org.HRP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MainController {

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public MainController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value="/addUser",method = RequestMethod.POST)
    public String addUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        if(userExists(username)) {
            return "User already exists!";
        }
        inMemoryUserDetailsManager.createUser(new User(username, password, new ArrayList<GrantedAuthority>()));
        return "User Added";
    }

    @RequestMapping(value="/updatePassword",method = RequestMethod.PUT)
    public String updatePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        inMemoryUserDetailsManager.changePassword(oldPassword, newPassword);
        return "Password Updated";
    }

    @RequestMapping(value="removeUser/{username}", method = RequestMethod.DELETE)
    public String removeUser(@PathVariable("username") String username) {
        if(userExists(username)) {
            inMemoryUserDetailsManager.deleteUser(username);
            return "User deleted";
        }
        return "User doesn't exist!";
    }

    public boolean userExists(String username ) {
        return inMemoryUserDetailsManager.userExists(username);
    }

}
