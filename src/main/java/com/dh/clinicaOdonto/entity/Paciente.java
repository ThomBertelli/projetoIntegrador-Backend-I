package com.dh.clinicaOdonto.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pacientes")

    public class Paciente {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nome;
        private String sobrenome;
        private String rg;
        private Timestamp dataCadastro;

        @OneToOne
        @JoinColumn(name = "id_endereco" , referencedColumnName = "id")
        private Endereco endereco;


}