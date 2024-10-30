package dev.lvpq.CS502052.Config;

import dev.lvpq.CS502052.Entity.User;
import dev.lvpq.CS502052.Enums.Role;
import dev.lvpq.CS502052.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Configuration
public class StartUp {
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner (UserRepository userRepository){
        return args -> {
            if (!userRepository.existsByEmail("manager@gmail.com")) {
                HashSet<String> roles = new HashSet<>();
                roles.add(Role.MANAGER.name());
                var user = User.builder()
                        .name("Manager")
                        .email("manager@gmail.com")
                        .password(passwordEncoder.encode("manager123"))
                        .roles(roles)
                        .build();
                userRepository.save(user);
                log.warn("Manager Account initialized");
            }
        };
    }
}
