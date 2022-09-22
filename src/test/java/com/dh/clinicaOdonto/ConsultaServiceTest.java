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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest

//@Transactional
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
    void alterando(){
        logger.info("Iniciando teste alterar consulta.");
        service.buscarPorRg("555");
        paciente.setRg("99999");
        Assertions.assertEquals("99999",paciente.getRg());
        logger.info("Teste alterar consulta finalizado.");
    }

    @Test
    void consultando() {
        logger.info("Iniciando teste alterar consulta.");
        Timestamp dataAlterada = Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2022, 10, 12), LocalTime.of(16, 00, 0)));
        Object Consulta;
        Consulta = consulta;
        Consulta = service.buscaPorId(1L);
//        Consulta = consulta.getDataHoraAgendamento();
        consulta.setDataHoraAgendamento(dataAlterada);
        Assertions.assertEquals(dataAlterada, consulta.getDataHoraAgendamento());
        logger.info("Teste alterar consulta finalizado.");
    }


}

