package com.subilete.demojwt.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subilete.demojwt.Entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username); 
    
    boolean existsByUsername(String username); // Validar si el usuario ya existe
    boolean existsByDni(String dni);
    boolean existsByEmail(String email);
}
