package com.feedlog.controller;

import com.feedlog.domain.User;
import com.feedlog.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class htmlController {

    @Autowired
    private final UserService service;

    public htmlController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public String findAllUsers(Model model){

        List<User> users =  service.findAllUsers();
        model.addAttribute("users",users);
        return "usersPage";
    }
    @GetMapping("/register")
    public String viewRegisterPage(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registerPage";
    }
    @PostMapping("/register")
    public String registerUser(Model model, @ModelAttribute("user") User user){
        User newUsers = service.addUser(user);
        model.addAttribute("users",newUsers);
        return "registerPage";
    }
}
