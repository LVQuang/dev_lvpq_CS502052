package dev.lvpq.CS502052.Config;

import dev.lvpq.CS502052.Entity.Brand;
import dev.lvpq.CS502052.Entity.Role;
import dev.lvpq.CS502052.Entity.User;
import dev.lvpq.CS502052.Enums.RoleFeature;
import dev.lvpq.CS502052.Repository.BrandRepository;
import dev.lvpq.CS502052.Repository.OTPRepository;
import dev.lvpq.CS502052.Repository.RoleRepository;
import dev.lvpq.CS502052.Repository.UserRepository;
import dev.lvpq.CS502052.Specification.UserSpec;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Configuration
public class StartUp {
    private static final String EMAIL_MANAGER = "manager@gmail.com";
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner (UserRepository userRepository,
                                         RoleRepository roleRepository,
                                         OTPRepository otpRepository,
                                         BrandRepository brandRepository){
        return args -> {
            InitializeRole(roleRepository);
            InitializeManager(userRepository, roleRepository);
            otpRepository.deleteAll();
            TestBrand(brandRepository);
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
        Specification<User> spec = Specification.where(null);
        spec.and(UserSpec.hasEmail(EMAIL_MANAGER, true));
        var users = userRepository.findAll(spec);

        if (users.isEmpty()) {
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

    void TestBrand(BrandRepository brandRepository) {
        var brand = Brand.builder()
            .name("Testing")
            .logoURL("#")
            .contractURL("#")
            .brandWebLink("#")
            .expirityContractDate(LocalDate.now().plus(1, ChronoUnit.YEARS))
            .build();

        brandRepository.save(brand);
    }
}
