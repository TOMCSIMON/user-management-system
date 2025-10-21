package com.tom.User_Management.repository;

import com.tom.User_Management.enums.RoleEnum;
import com.tom.User_Management.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(RoleEnum roleName);
}
