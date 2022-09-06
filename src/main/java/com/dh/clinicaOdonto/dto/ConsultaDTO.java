package com.dh.clinicaOdonto.dto;

import com.dh.clinicaOdonto.entity.Consulta;
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

public class ConsultaDTO {
    private String infosConsulta;

    public ConsultaDTO (Consulta consulta){
        infosConsulta = consulta.getPacienteId()+", sua consulta está marcada para " + consulta.getData_consulta() + "com o dentista " +consulta.getDentistaId();
    }


}
