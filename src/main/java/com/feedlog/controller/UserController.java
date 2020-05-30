package com.feedlog.controller;

import com.feedlog.domain.User;
import com.feedlog.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping()
    public List<User> findAllUsers(){
        return service.findAllUsers();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable(required = false) int id){
        return service.findById(id);
    }

    @GetMapping(value="/find", params = "userName")
    public User findByUserName(String userName){
        return service.findByUserName(userName);
    }

    @PostMapping("/sign-up")
    public User addUser(@RequestBody User user){
        return service.addUser(user);
    }

    @PutMapping("/{id}")
    public User replaceUser(@RequestBody User user,@PathVariable int id){
        return service.replaceUser(user,id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        service.deleteUser(id);
    }




}
