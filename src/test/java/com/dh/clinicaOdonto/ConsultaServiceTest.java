package com.dh.clinicaOdonto;

import com.dh.clinicaOdonto.entity.Consulta;
import com.dh.clinicaOdonto.entity.Dentista;
import com.dh.clinicaOdonto.entity.Endereco;
import com.dh.clinicaOdonto.entity.Paciente;
import com.dh.clinicaOdonto.service.ConsultaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest
@Transactional

class ConsultaServiceTest {

    @Autowired
    ConsultaService service;

    static Consulta consulta;
    static Dentista dentista;

    static Endereco endereco;
    static Paciente paciente;

    @BeforeAll
    static void doBefore(){
        endereco = new Endereco();
        endereco.setCidade("Timbó");
        endereco.setEstado("SC");
        endereco.setNumero("578");
        endereco.setRua("Av Tancredo Neves");


        dentista = new Dentista();
        dentista.setMatricula(5555);
        dentista.setNome("Carlos");
        dentista.setSobrenome("Correa");

        paciente=new Paciente();
        paciente.setNome("José");
        paciente.setSobrenome("Santos");
        paciente.setEndereco(endereco);
        paciente.setRg("00000");

        Timestamp data = Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2022, 12, 7), LocalTime.of(8, 45, 0)));


        consulta = new Consulta();
        consulta.setDentista(dentista);
        consulta.setPaciente(paciente);
        consulta.setDataHoraAgendamento(data);

    }

        @Test
        void salvando(){
            Consulta consultaSalva = new Consulta();
            consultaSalva = service.salvar(consulta);

            Assertions.assertNotNull(consultaSalva.getId());
        }

}