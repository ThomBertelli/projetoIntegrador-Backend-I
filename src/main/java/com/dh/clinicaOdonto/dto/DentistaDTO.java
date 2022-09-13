package com.dh.clinicaOdonto.dto;

import com.dh.clinicaOdonto.entity.Dentista;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)

public class DentistaDTO {
//    private String nome;
//    private String sobrenome;

    private String nomeCompleto;

    public DentistaDTO(Dentista dentista){
//        this.nome = dentista.getNome();
//        this.sobrenome = dentista.getSobrenome();
        this.nomeCompleto = dentista.getNome() + " " + dentista.getSobrenome();
    }
}
