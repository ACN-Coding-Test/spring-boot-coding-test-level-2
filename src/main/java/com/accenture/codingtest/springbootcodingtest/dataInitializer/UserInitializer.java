package com.accenture.codingtest.springbootcodingtest.dataInitializer;

import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.model.Role;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public UserInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setUsername("projectOwner");
        user1.setPassword(passwordEncoder().encode("1234"));
        user1.getRoles().add(Role.PROJECT_OWNER);
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("admin");
        user2.setPassword(passwordEncoder().encode("1234"));
        user2.getRoles().add(Role.ADMIN);
        userRepository.save(user2);

        User user3 = new User();
        user3.setUsername("teamMember");
        user3.setPassword(passwordEncoder().encode("1234"));
        user3.getRoles().add(Role.TEAM_MEMBER);
        userRepository.save(user3);
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
