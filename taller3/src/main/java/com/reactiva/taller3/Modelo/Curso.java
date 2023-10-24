package com.reactiva.taller3.Modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table("curso")
public class Curso {
    @Id
    private Integer id;

    @Column("materia_id")
    private Integer materia;

    @Column("profesor_id")
    private Integer profesor;

    @Column("numero")
    private String numero;

    @Column("estudiante_id")
    private Integer estudiante;

    @Column("fecha_inicio")
    private Date inicio;

    @Column("fecha_fin")
    private Date fin;

}
