package com.dh.clinicaOdonto;

import com.dh.clinicaOdonto.entity.Consulta;
import com.dh.clinicaOdonto.entity.Dentista;
import com.dh.clinicaOdonto.entity.Endereco;
import com.dh.clinicaOdonto.entity.Paciente;
import com.dh.clinicaOdonto.service.ConsultaService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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

        paciente = new Paciente();
        paciente.setNome("José");
        paciente.setSobrenome("Santos");
        paciente.setEndereco(endereco);
        paciente.setRg("555");

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
    void alterando(){
        logger.info("Iniciando teste alterar consulta.");
        List<Consulta> consultas = service.buscarPorRg("555");
        consultas.get(0).getPaciente().setRg("99999");
        Consulta consultaAlterada = service.alterar(consultas.get(0));
        Assertions.assertEquals("99999",consultaAlterada.getPaciente().getRg());
        logger.info("Teste alterar consulta finalizado.");
    }

}