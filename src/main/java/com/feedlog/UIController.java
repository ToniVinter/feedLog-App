package com.feedlog;

import com.feedlog.domain.Post;
import com.feedlog.domain.User;
import com.feedlog.domain.UserProfile;
import com.feedlog.service.PostService;
import com.feedlog.service.UserProfileService;
import com.feedlog.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UIController {

    private final UserService userService;
    private final PostService postService;
    private final UserProfileService profileService;

    public UIController(UserService userService, PostService postService, UserProfileService profileService) {
        this.userService = userService;
        this.postService = postService;
        this.profileService = profileService;
    }

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("users",userService.findAllUsers());
        return "login";
    }

    @GetMapping("home")
    public String home(Model homeModel, Model post){
        Post createPost = new Post();
        post.addAttribute("createPost", createPost);
        homeModel.addAttribute("posts",postService.findAll());



        return "home";
    }
    @GetMapping("profilesPage")
    public String profiles(Model profileModel){


        profileModel.addAttribute("userProfilesHome",profileService.findAll());
        return "profilesPage";
    }

    @GetMapping("registerPage")
    public String showRegistrationPage(Model registerModel){
        User user = new User();
        registerModel.addAttribute("user",user);
        return "registerPage";
    }

    @PostMapping("registerPage")
    public String registerProfile(@ModelAttribute("user") User user){
        System.out.println(user);
        userService.addUser(user);
        return "registerPage";
    }


    @GetMapping("profilesPage/{userProfileId}")
    public String showProfilePage(@ModelAttribute UserProfile userProfile, Model model, @PathVariable("userProfileId") Integer profileId){
        userProfile = profileService.findById(profileId);
        model.addAttribute("userProfile",userProfile);
        return "profilePage";
    }

    @GetMapping("createProfile")
    public String createProfile(Model profileModel, Model userModel){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userModel.addAttribute("user", authentication.getPrincipal());


        UserProfile userProfile = new UserProfile();
        profileModel.addAttribute("userProfile", userProfile);


        return "createProfile";
    }

    @PostMapping("createProfile")
    public String createProfile(@ModelAttribute("userProfile") UserProfile userProfile){
        profileService.addUserProfile(userProfile);
        return "home";
    }


    @PostMapping("home")
    public String createPost(@ModelAttribute("createPost") Post post){
        postService.addPost(post);
        return "home";
    }




}
