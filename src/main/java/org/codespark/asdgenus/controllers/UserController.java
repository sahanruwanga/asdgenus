package org.codespark.asdgenus.controllers;

import org.codespark.asdgenus.models.User;
import org.codespark.asdgenus.services.database_services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public String addUser(@RequestParam("name") String name, @RequestParam("email") String email,
                        @RequestParam("password") String password) {
        this.getUserService().saveUser(name, email, password);
        return "User saved successfully!";
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
