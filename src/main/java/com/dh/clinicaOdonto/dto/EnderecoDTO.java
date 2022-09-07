package com.dh.clinicaOdonto.dto;

import com.dh.clinicaOdonto.entity.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)

public class EnderecoDTO {
    private String rua;
    private String numero;

//    private String enderecoCompleto;

    public EnderecoDTO(Endereco endereco){
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
   //     this.enderecoCompleto = endereco.getRua() + ", " + endereco.getNumero();
    }
}
