package com.dh.clinicaOdonto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

    public class Endereco {
        private int id;
        private String rua;
        private String numero;
        private String cidade;
        private String estado;
}
