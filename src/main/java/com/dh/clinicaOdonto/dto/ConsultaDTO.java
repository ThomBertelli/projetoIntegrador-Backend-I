package com.dh.clinicaOdonto.dto;

import com.dh.clinicaOdonto.entity.Consulta;
import com.dh.clinicaOdonto.entity.Dentista;
import com.dh.clinicaOdonto.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.sql.Timestamp;
import java.time.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)

public class ConsultaDTO {
    private LocalDateTime dataHoraAgendamento;

    private Dentista dentista;

    private Paciente paciente;

   public void setDataHoraAgendamento(Timestamp dataHoraAgendamento){
        this.dataHoraAgendamento = Instant.ofEpochMilli(dataHoraAgendamento.getTime()).atZone(ZoneId.of("UTC-03")).toLocalDateTime();
   }
}
