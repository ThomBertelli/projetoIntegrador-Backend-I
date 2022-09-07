package com.dh.clinicaOdonto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "id_endereco" , referencedColumnName = "id")
        private Endereco endereco;

        private String rg;
        private Date dataCadastro;
}