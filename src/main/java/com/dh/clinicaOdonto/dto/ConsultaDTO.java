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
    public String infosConsulta;
    private LocalDateTime dataHoraAgendamento;
//    private LocalTime horaAgendamento;
//    private LocalDate dataAgendamento;

    private Dentista dentista;

    private Paciente paciente;

//    public ConsultaDTO (Consulta consulta){
//        infosConsulta = consulta.getPaciente().getNome()+", sua consulta está marcada para " + dataHoraAgendamento + " com o(a) dentista " + consulta.getDentista().getNome();
//    }

   public void setDataHoraAgendamento(Timestamp dataHoraAgendamento){
        this.dataHoraAgendamento = Instant.ofEpochMilli(dataHoraAgendamento.getTime()).atZone(ZoneId.of("UTC-03")).toLocalDateTime();
       //        this.dataAgendamento = this.dataHoraAgendamento.toLocalDate();
       //        this.horaAgendamento = this.dataHoraAgendamento.toLocalTime();
       infosConsulta = paciente.getNome() + ", sua consulta está marcada para " + dataHoraAgendamento + " com o(a) dentista " + dentista.getNome();

   }
}
