package com.dh.clinicaOdonto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

    public class Consulta {
        private int id;
        private Dentista dentista;
        private Paciente paciente;
        private LocalDate data_consulta;

}
