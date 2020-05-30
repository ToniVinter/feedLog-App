package com.feedlog.controller;

import com.feedlog.domain.User;
import com.feedlog.domain.UserProfile;
import com.feedlog.service.UserProfileService;
import com.feedlog.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserProfileController {
    private final UserProfileService profileService;
    private final UserService userService;
    public UserProfileController(UserProfileService service, UserService userService) {
        this.profileService = service;
        this.userService = userService;
    }

    @GetMapping("profiles")
    public List<UserProfile> findAll(){
       return profileService.findAll();
    }

    @PostMapping("users/{id}/userprofile")
    public UserProfile addUserProfile(@PathVariable int id, @RequestBody UserProfile userProfile){
        User user = userService.findById(id);
        return profileService.addUserProfile(user, userProfile);
    }
}
