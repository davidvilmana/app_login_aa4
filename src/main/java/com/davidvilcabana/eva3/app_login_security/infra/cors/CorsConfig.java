package com.davidvilcabana.eva3.app_login_security.infra.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// CorsConfig es una clase de configuración que define la configuración CORS para la aplicación.
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    //Configura las reglas CORS para la aplicación.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "DELETE", "PUT");
    }
}
