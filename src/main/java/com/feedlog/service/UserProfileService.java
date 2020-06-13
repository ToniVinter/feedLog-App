package com.feedlog.service;

import com.feedlog.domain.User;
import com.feedlog.domain.UserProfile;
import com.feedlog.exceptions.UserNotFound;
import com.feedlog.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserProfileService {
    private final UserProfileRepository repository;

    public UserProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    public List<UserProfile> findAll(){
        return repository.findAll();
    }

    public UserProfile findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFound("user not found"));
    }


    public UserProfile addUserProfile(UserProfile profile){
       return repository.save(profile);
    }
}
