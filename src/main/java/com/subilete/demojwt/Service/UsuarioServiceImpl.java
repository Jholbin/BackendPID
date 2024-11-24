package com.subilete.demojwt.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.subilete.demojwt.Auth.AuthResponse;
import com.subilete.demojwt.Auth.JwtService;
import com.subilete.demojwt.Entity.Role;
import com.subilete.demojwt.Entity.User;
import com.subilete.demojwt.Repository.RoleRepository;
import com.subilete.demojwt.Repository.UserRepository;
import com.subilete.demojwt.User.RegisterRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UserService {
	
	private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
	
	
	@Override
	public AuthResponse register(RegisterRequest request) {
		
		if (userRepository.existsByDni(request.getDni())) {
	        throw new IllegalArgumentException("El DNI ya está registrado");
	    }

	    if (userRepository.existsByEmail(request.getEmail())) {
	        throw new IllegalArgumentException("El Email ya esta registrado");
	    }
	
		if (userRepository.existsByUsername(request.getUsername())) {
	        throw new IllegalArgumentException("El usuario ya existe");
	    }

	 // Buscar el rol en la base de datos, si no existe, lanzamos una excepción o lo creamos
    Role role = roleRepository.findByName(request.getRole())
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName(request.getRole());
                    return roleRepository.save(newRole); // Guardamos el nuevo rol si no existe
                });

    // Crear y guardar el usuario
    User user = User.builder()
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .dni(request.getDni())
        .email(request.getEmail())
        .role(role)
        .build();

    userRepository.save(user);

    // Generar el token
    String token = jwtService.getToken(user);

    // Retornar la respuesta
    return AuthResponse.builder()
        .token(token)
        .build();
	}
	

}
