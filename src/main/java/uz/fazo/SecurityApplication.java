package uz.fazo;

import uz.fazo.user.User;
import uz.fazo.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static uz.fazo.user.Role.ADMIN;

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
            Optional<User> user = userRepository.findByUsername("mainadmin");

            if (user.isEmpty()) {
                User admin = User.builder().role(ADMIN)
                        .username("mainadmin")
                        .password(passwordEncoder.encode("ozkojadmin"))
                        .enabled(true).build();
                userRepository.save(admin);
            }

        };
    }
}
