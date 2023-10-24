package com.reactiva.taller3.Modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table("persona")
public class Persona {

    @Id
    private Integer id;

    @Column("nombre")
    private String nombre;

    @Column("apellido")
    private String apellido;

    @Column("correo")
    private String correo;

    @Column("rol")
    private Character rol;

}
