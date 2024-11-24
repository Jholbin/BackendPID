package com.subilete.demojwt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subilete.demojwt.Entity.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
	
	Optional<Role> findByName(String name);

}
