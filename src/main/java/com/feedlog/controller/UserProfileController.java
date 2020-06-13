//package com.feedlog.controller;
//
//import com.feedlog.domain.User;
//import com.feedlog.domain.UserProfile;
//import com.feedlog.service.UserProfileService;
//import com.feedlog.service.UserService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("profilesPage/{userProfileId}")
//public class UserProfileController {
//    private final UserProfileService profileService;
//    private final UserService userService;
//    public UserProfileController(UserProfileService service, UserService userService) {
//        this.profileService = service;
//        this.userService = userService;
//    }
//
//    @ModelAttribute
//    public UserProfile userProfile(@PathVariable("userProfileId") Integer profileId) {
//        return profileService.findById(profileId);
//    }
//
//    @GetMapping
//    public String showProfilePage(@ModelAttribute UserProfile userProfile, Model model){
//        model.addAttribute("userProfile",userProfile);
//        return "profilePage";
//    }
//}
