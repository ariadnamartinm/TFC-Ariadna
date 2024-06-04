package com.s2daw.reactspringbackend.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity // Anotación que indica que esta clase es una entidad JPA
@Table(name = "usuarios") // Nombre de la tabla en la base de datos
@ToString @EqualsAndHashCode // Genera automáticamente los métodos toString() y equals() con los campos de la clase
public class Usuario {

    @Id // Anotación que indica que este campo es la clave primaria
    @GeneratedValue(strategy=GenerationType.IDENTITY) // Generación automática de la clave primaria
    @Getter @Setter @Column(name = "id") // Genera automáticamente getter y setter para este campo
    private Long id;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "apellido")
    private String apellido;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "telefono")
    private String telefono;

    @Getter @Setter @Column(name = "password")
    private String password;
}
