package com.feedlog.repository;

import com.feedlog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
//    Post findByUserProfileId(int id);
//    Post findByUserProfileName(String fullName);
}
