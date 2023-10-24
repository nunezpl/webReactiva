package com.reactiva.taller3.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Table("nota")
public class Nota {
    @Id
    private Integer id;

    @Column("materia_id")
    private String materia;

    @Column("profesor_id")
    private Integer profesor;

    @Column("estudiante_id")
    private Integer estudiante;

    @Column("observacion")
    private String observacion;

    @Column("valor")
    private Number valor;

}