package dev.lvpq.CS502052.Config;

import dev.lvpq.CS502052.Entity.Role;
import dev.lvpq.CS502052.Entity.User;
import dev.lvpq.CS502052.Enums.RoleFeature;
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
                var user = User.builder()
                        .username("Manager")
                        .email("manager@gmail.com")
                        .password(passwordEncoder.encode("manager123"))
                        .build();

                var roleFeature = RoleFeature.MANAGER;
                var role = Role.builder()
                        .name(roleFeature.getName())
                        .description(roleFeature.getDescription())
                        .meta(roleFeature.getMeta())
                        .build();

                user.addRole(role);
                userRepository.save(user);
            }
        };
    }
}
