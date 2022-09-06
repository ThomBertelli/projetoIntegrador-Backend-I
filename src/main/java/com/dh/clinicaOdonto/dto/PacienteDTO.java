package com.dh.clinicaOdonto.dto;

import com.dh.clinicaOdonto.entity.Dentista;
import com.dh.clinicaOdonto.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)

public class PacienteDTO {
    private String nomeCompleto;


    public PacienteDTO(Paciente paciente){

        this.nomeCompleto = paciente.getNome() + " " + paciente.getSobrenome();
    }

}
