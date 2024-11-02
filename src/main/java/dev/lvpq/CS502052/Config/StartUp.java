package dev.lvpq.CS502052.Config;

import dev.lvpq.CS502052.Entity.Role;
import dev.lvpq.CS502052.Entity.User;
import dev.lvpq.CS502052.Enums.RoleFeature;
import dev.lvpq.CS502052.Repository.RoleRepository;
import dev.lvpq.CS502052.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Configuration
public class StartUp {
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner (UserRepository userRepository, RoleRepository roleRepository){
        return args -> {
            InitializeRole(roleRepository);
            InitializeManager(userRepository, roleRepository);
        };
    }

    void InitializeRole(RoleRepository roleRepository) {
        var roles = new ArrayList<Role>();
        if (roleRepository.findAll().isEmpty()) {
            for (RoleFeature roleFeature : RoleFeature.values()) {
                roles.add(Role.builder()
                        .name(roleFeature.getName())
                        .description(roleFeature.getDescription())
                        .build());
            }
        }
        roleRepository.saveAll(roles);
    }

    void InitializeManager(UserRepository userRepository,
                           RoleRepository roleRepository) {
        if (!userRepository.existsByEmail("manager@gmail.com")) {
            var user = User.builder()
                    .username("Manager")
                    .email("manager@gmail.com")
                    .password(passwordEncoder.encode("manager123"))
                    .build();

            var role = roleRepository.findByName(RoleFeature.MANAGER.getName()).orElse(null);
            user.addRole(role);
            userRepository.save(user);
        }
    }
}
