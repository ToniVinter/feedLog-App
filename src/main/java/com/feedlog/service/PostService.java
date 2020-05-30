package com.feedlog.service;

import com.feedlog.domain.Post;
import com.feedlog.exceptions.UserNotFound;
import com.feedlog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostService {
    private final PostRepository repository;


    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> findAll(){
        return repository.findAll();
    }

    public Post findById(int id){
        return repository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

//    public Post findByUserProfileId(int id){
//        return repository.findByUserProfileId(id);
//    }
//
//    public Post findByUserProfileName(String fullName){
//        return repository.findByUserProfileName(fullName);
//    }

    public Post addPost(Post post){
        return repository.save(post);
    }

}
