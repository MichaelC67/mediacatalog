package com.example.mediacatalog.service;

import com.example.mediacatalog.model.ERole;
import com.example.mediacatalog.model.Role;
import com.example.mediacatalog.model.User;
import com.example.mediacatalog.repository.RoleRepository;
import com.example.mediacatalog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class InitService implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName(ERole.ROLE_USER).isEmpty()) {
            Role userRole = new Role(ERole.ROLE_USER);
            roleRepository.save(userRole);
        }

        if (roleRepository.findByName(ERole.ROLE_ADMIN).isEmpty()) {
            Role adminRole = new Role(ERole.ROLE_ADMIN);
            roleRepository.save(adminRole);
        }

        if (userRepository.findByUsername("admin").isEmpty()) {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("password"));
            adminUser.setEmail("admin@example.com");

            Set<Role> adminRoles = new HashSet<>();
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Role not found."));
            adminRoles.add(adminRole);
            adminUser.setRoles(adminRoles);

            userRepository.save(adminUser);
        }
    }
}
