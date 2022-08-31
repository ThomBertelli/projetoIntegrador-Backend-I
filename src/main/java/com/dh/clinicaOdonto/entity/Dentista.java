package com.dh.clinicaOdonto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

    public class Dentista {
        private int id;
        private String nome;
        private String sobrenome;
        private int matricula;
}
