package com.reactiva.taller3.Modelo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Data
@Table("persona")
public class Persona  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
