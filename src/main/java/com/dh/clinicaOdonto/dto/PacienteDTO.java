package com.dh.clinicaOdonto.dto;


import com.dh.clinicaOdonto.entity.Endereco;
import com.dh.clinicaOdonto.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Timestamp;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)

public class PacienteDTO {
    private String nomeCompleto;
    private String rg;
    private Timestamp dataCadastro;
   private Endereco endereco;

    public PacienteDTO(Paciente paciente){
        this.nomeCompleto = paciente.getNome() + " " + paciente.getSobrenome();
        this.rg = paciente.getRg();
        this.dataCadastro = paciente.getDataCadastro();
        this.endereco= paciente.getEndereco();
    }

}
