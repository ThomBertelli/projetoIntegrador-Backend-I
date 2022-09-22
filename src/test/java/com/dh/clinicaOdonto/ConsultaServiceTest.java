package com.dh.clinicaOdonto;

import com.dh.clinicaOdonto.dto.ConsultaDTO;
import com.dh.clinicaOdonto.entity.Consulta;
import com.dh.clinicaOdonto.entity.Dentista;
import com.dh.clinicaOdonto.entity.Endereco;
import com.dh.clinicaOdonto.entity.Paciente;
import com.dh.clinicaOdonto.exception.ResourceNotFoundException;
import com.dh.clinicaOdonto.service.ConsultaService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class ConsultaServiceTest {

    Logger logger  = Logger.getLogger(ConsultaServiceTest.class);

    @Autowired
    ConsultaService service;

    static Endereco endereco;
    static Dentista dentista;

    static Paciente paciente;

    static Consulta consulta;

    @BeforeAll
    static void doBefore() {
        endereco = new Endereco();
        endereco.setCidade("Timbó");
        endereco.setEstado("SC");
        endereco.setNumero("578");
        endereco.setRua("Av Tancredo Neves");


        dentista = new Dentista();
        dentista.setMatricula(5555);
        dentista.setNome("Carlos");
        dentista.setSobrenome("Correa");

        Timestamp dataCadastro = Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2022, 9, 21), LocalTime.of(14, 30, 0)));

        paciente = new Paciente();
        paciente.setNome("José");
        paciente.setSobrenome("Santos");
        paciente.setEndereco(endereco);
        paciente.setRg("555");
        paciente.setDataCadastro(dataCadastro);

        Timestamp data = Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2022, 12, 7), LocalTime.of(8, 45, 0)));


        consulta = new Consulta();
        consulta.setDentista(dentista);
        consulta.setPaciente(paciente);
        consulta.setDataHoraAgendamento(data);

    }

    @Test
    void salvando() {
        logger.info("Iniciando teste salvar consulta.");
        Consulta consultaSalva = new Consulta();
        consultaSalva = service.salvar(consulta);

        Assertions.assertNotNull(consultaSalva.getId());
        logger.info("Teste salvar consulta finalizado.");
    }

    @Test
    void alterando() throws ResourceNotFoundException {
        consulta = service.salvar(consulta);
        LocalDateTime dataAlt = LocalDateTime.of(2022,12,22,15,20,15,00);
        logger.info("Iniciando teste alterar consulta.");
        ConsultaDTO consultaDTO = service.buscaPorId(1L);
        System.out.println(consultaDTO);
       consultaDTO.setDataHoraAgendamento(Timestamp.valueOf(dataAlt));
        Assertions.assertEquals(dataAlt,consultaDTO.getDataHoraAgendamento());
        logger.info("Teste alterar consulta finalizado.");
    }

    @Test
    void buscaPorId() throws ResourceNotFoundException {
        consulta = service.salvar(consulta);

        ConsultaDTO consultaDTO = service.buscaPorId(1L);
        System.out.println(consultaDTO);

    }


}