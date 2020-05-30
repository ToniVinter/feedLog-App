package com.feedlog.controller;

import com.feedlog.domain.Post;
import com.feedlog.domain.UserProfile;
import com.feedlog.service.PostService;
import com.feedlog.service.UserProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {
    private final UserProfileService profileService;
    private final PostService postService;

    public PostController(UserProfileService profileService, PostService postService) {
        this.profileService = profileService;
        this.postService = postService;
    }

    @GetMapping
    public List<Post> findAll(){
        return postService.findAll();
    }

    @PostMapping
    public Post savePost(@RequestBody Post post){
        return postService.addPost(post);
    }

}
