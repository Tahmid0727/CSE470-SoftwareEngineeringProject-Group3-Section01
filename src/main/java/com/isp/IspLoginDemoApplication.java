package com.isp;

import com.isp.entity.User;
import com.isp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class IspLoginDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(IspLoginDemoApplication.class, args);
    }

    // Adds one demo account the first time the application runs.
    @Bean
    CommandLineRunner createDemoUser(UserRepository users, PasswordEncoder encoder) {
        return args -> {
            if (users.findByEmail("admin@isp.com").isEmpty()) {
                User user = new User();
                user.setFullName("Demo Administrator");
                user.setEmail("admin@isp.com");
                user.setPassword(encoder.encode("admin123"));
                user.setRole("ADMIN");
                user.setActive(true);
                users.save(user);
            }
        };
    }
}
