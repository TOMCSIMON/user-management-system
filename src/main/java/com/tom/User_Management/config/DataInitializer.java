package com.tom.User_Management.config;

import com.tom.User_Management.enums.RoleEnum;
import com.tom.User_Management.model.Role;
import com.tom.User_Management.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void initliazeRoles() {

        if(roleRepository.count() == 0) {

            roleRepository.save(new Role(null, RoleEnum.ROLE_USER));
            roleRepository.save(new Role(null, RoleEnum.ROLE_ADMIN));
        }
    }
}
