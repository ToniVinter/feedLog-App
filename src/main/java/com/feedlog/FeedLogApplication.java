package com.feedlog;

import com.feedlog.domain.Post;
import com.feedlog.domain.User;
import com.feedlog.domain.UserProfile;
import com.feedlog.repository.PostRepository;
import com.feedlog.repository.UserProfileRepository;
import com.feedlog.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
public class FeedLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedLogApplication.class, args);
    }

    @Bean
    CommandLineRunner atStartup(UserRepository repo, PostRepository postRepository, UserProfileRepository profileRepository) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return args -> {
            User user = new User("Toni", "USER", passwordEncoder.encode("Toni123"), true);

            repo.save(user);
            repo.save(new User("Smith", "USER", "Smith123", true,
                    new UserProfile("Tom Smith", "1454", "Male", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")));
            repo.save(new User("Williams", "USER", "Williams123", true,
                    new UserProfile("John Williams", "13000", "Male", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")));

            repo.findAll()
                    .forEach(System.out::println);

            postRepository.save(new Post("Trip in Arizona",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie nisi lorem, vel pharetra lorem congue sit amet. In hac habitasse platea dictumst. Maecenas vel nulla vel urna molestie lobortis a eu arcu. Ut non efficitur nisi. Nunc porttitor commodo nisi, vel venenatis lacus hendrerit vitae. Mauris est dolor, rutrum id ipsum eget, consectetur euismod risus. Nunc id ultricies velit, at volutpat sem.",
                    new Timestamp(System.currentTimeMillis()),
					profileRepository.findById(1).get()
            ));
            System.out.println(profileRepository.findById(1).get());
            postRepository.save(new Post("Trip in Phoenix",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie nisi lorem, vel pharetra lorem congue sit amet. In hac habitasse platea dictumst. Maecenas vel nulla vel urna molestie lobortis a eu arcu. Ut non efficitur nisi. Nunc porttitor commodo nisi, vel venenatis lacus hendrerit vitae. Mauris est dolor, rutrum id ipsum eget, consectetur euismod risus. Nunc id ultricies velit, at volutpat sem.",
                    new Timestamp(System.currentTimeMillis()),
                    repo.findByUsername("Smith").getUserProfile()
            ));

            postRepository.save(new Post("Trip in Sedona",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie nisi lorem, vel pharetra lorem congue sit amet. In hac habitasse platea dictumst. Maecenas vel nulla vel urna molestie lobortis a eu arcu. Ut non efficitur nisi. Nunc porttitor commodo nisi, vel venenatis lacus hendrerit vitae. Mauris est dolor, rutrum id ipsum eget, consectetur euismod risus. Nunc id ultricies velit, at volutpat sem.",
                    new Timestamp(System.currentTimeMillis()),
                    repo.findByUsername("Toni").getUserProfile()
            ));

            postRepository.findAll()
                    .forEach(System.out::println);
        };
    }

    public static Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            public String getAuthority() {
                return "ROLE_USER";
            }
        };
        grantedAuthorities.add(grantedAuthority);
        return grantedAuthorities;
    }
}
