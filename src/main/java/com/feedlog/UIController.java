package com.feedlog;

import com.feedlog.domain.User;
import com.feedlog.domain.UserProfile;
import com.feedlog.service.PostService;
import com.feedlog.service.UserProfileService;
import com.feedlog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public String home(Model homeModel){
        homeModel.addAttribute("posts",postService.findAll());
        homeModel.addAttribute("postForProfiles",profileService.findAll());
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
        return "home";
    }

}
