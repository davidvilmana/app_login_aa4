package com.davidvilcabana.eva3.app_login_security.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// anotacion entity Una entidad representa una tabla almacenada en una base de datos. Cada instancia de una entidad representa una fila en la tabla
@Entity
@Table(name = "users")//el nombre de la tabla en la base de datos
@Getter//Genera automáticamente los métodos getter y setter para todos los campos
@Setter
@AllArgsConstructor// Genera un constructor con un parámetro para cada campo en la clase
@NoArgsConstructor//Genera un constructor sin parámetros.
public class User {

    @Id//define la clave principal
    @GeneratedValue(strategy = GenerationType.UUID)
    //atributos
    private String id;
    private String name;
    private String email;
    private String password;

}
