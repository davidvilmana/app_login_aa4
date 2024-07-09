package com.davidvilcabana.eva3.app_login_security.infra.segurity;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.davidvilcabana.eva3.app_login_security.domain.user.User;
import com.davidvilcabana.eva3.app_login_security.repositories.UserRepository;



//CustomUserDetailsService es un servicio que implementa UserDetailsService para
 //cargar detalles del usuario desde la base de datos.
@Component
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    // Carga los detalles del usuario por nombre de usuario (correo electrónico).
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

}
