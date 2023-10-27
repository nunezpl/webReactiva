package com.reactiva.taller3.Modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table("materia")
public class Materia {
    @Id
    private Integer id;

    @Column("nombre")
    private String nombre;

    @Column("creditos")
    private int creditos;
}
