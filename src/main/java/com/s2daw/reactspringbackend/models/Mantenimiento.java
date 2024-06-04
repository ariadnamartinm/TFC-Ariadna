package com.s2daw.reactspringbackend.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "mantenimientos")
@ToString @EqualsAndHashCode
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Long id;

    @Getter @Setter @Column(name = "numero_oficina")
    private String numeroOficina;

    @Getter @Setter @Column(name = "kilometros")
    private Double kilometros;

    @Getter @Setter @Column(name = "desplazamientos")
    private Integer desplazamientos;

    @Getter @Setter @Column(name = "fecha")
    private Date fecha;

    // Constructor vacío (necesario para JPA)
    public Mantenimiento() {
    }

    // Constructor con parámetros
    public Mantenimiento(String numeroOficina, Double kilometros, Integer desplazamientos, Date fecha) {
        this.numeroOficina = numeroOficina;
        this.kilometros = kilometros;
        this.desplazamientos = desplazamientos;
        this.fecha = fecha;
    }
}
