package com.ProgettoIng.FedericoIoan.repository;

import com.ProgettoIng.FedericoIoan.model.ERole;
import com.ProgettoIng.FedericoIoan.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}