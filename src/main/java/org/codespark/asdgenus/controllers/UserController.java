package org.codespark.asdgenus.controllers;

import org.codespark.asdgenus.dtos.UserDTO;
import org.codespark.asdgenus.services.database_services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> register(@RequestBody UserDTO userDTO) {

        return new ResponseEntity<>(this.getUserService().registerUser(userDTO), HttpStatus.CREATED);
    }

    @PostMapping(path = "/login", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> login(@RequestBody UserDTO userDTO) {

        return new ResponseEntity<>(this.getUserService().loginUser(userDTO), HttpStatus.OK);
    }

    @GetMapping("/get/{email}")
    public int getUser(@PathVariable("email") String email){

        int id = userService.getUser(email);
        return id;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
