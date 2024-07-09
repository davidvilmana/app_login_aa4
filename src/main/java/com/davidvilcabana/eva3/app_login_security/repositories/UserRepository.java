package com.davidvilcabana.eva3.app_login_security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidvilcabana.eva3.app_login_security.domain.user.User;


 //UserRepository es una interfaz que extiende JpaRepository para proporcionar operaciones CRUD
 // y consultas personalizadas para la entidad User.
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
        // Busca un usuario por su correo electrónico.
}
