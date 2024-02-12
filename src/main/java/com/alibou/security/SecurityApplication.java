package com.alibou.security;

import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.alibou.security.user.Role.ADMIN;

@SpringBootApplication
@AllArgsConstructor

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityApplication {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);

    }

    @Bean
    public CommandLineRunner commandLineRunner(
    ) {
        return args -> {
            Optional<User> user = userRepository.findByEmail("xakimovoatbek@gmail.com");

            if (user.isEmpty()) {
                User otabek = User.builder().role(ADMIN)
                        .email("xakimovoatbek@gmail.com")
                        .firstname("ernf")
                        .lastname("wrkjfn")
                        .password(passwordEncoder.encode("otabek"))
                        .enabled(true).build();
                userRepository.save(otabek);
            }

        };
    }
}
