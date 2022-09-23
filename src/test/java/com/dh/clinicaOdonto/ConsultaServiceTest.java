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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

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
    void salvar() {
        logger.info("Iniciando teste salvar consulta.");
        Consulta consultaSalva = service.salvar(consulta);
        Assertions.assertNotNull(consultaSalva.getId());
        logger.info("Teste salvar consulta finalizado.");
    }

    @Test
    void alterar() throws ResourceNotFoundException {
        logger.info("Iniciando teste alterar consulta.");
        consulta = service.salvar(consulta);
        LocalDateTime dataAlt = LocalDateTime.of(2022,12,22,15,20,15,00);
        ConsultaDTO consultaDTO = service.buscaPorId(consulta.getId());
        System.out.println(consultaDTO);
        consultaDTO.setDataHoraAgendamento(Timestamp.valueOf(dataAlt));
        assertEquals(dataAlt,consultaDTO.getDataHoraAgendamento());
        logger.info("Teste alterar consulta finalizado.");
    }

    @Test
    void buscaPorId() throws ResourceNotFoundException {
        logger.info("Iniciando teste busca por id consulta.");
        consulta = service.salvar(consulta);
        ConsultaDTO consultaDTO = service.buscaPorId(2L);
        assertEquals(2L,consultaDTO.getPaciente().getId());
        logger.info("Teste busca por id consulta finalizado.");
    }

    @Test
    void excluir() throws ResourceNotFoundException {
        logger.info("Iniciando teste excluir consulta.");
        consulta = service.salvar(consulta);
        service.excluir(consulta.getId());
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class, ()-> service.buscaPorId(consulta.getId()));

        assertTrue(exception.getMessage().contains("Erro ao buscar consulta, id da consulta não existe"));
        logger.info("Teste excluir consulta finalizado.");

    }
    @Test
    void buscarTodos(){
        logger.info("Iniciando teste buscar todos consulta.");
        consulta = service.salvar(consulta);
        List<ConsultaDTO> listaConsulta = service.buscarTodos();
        assertTrue(listaConsulta.size() > 0);
        logger.info("Teste buscar todos consulta finalizado.");

    }

    @Test
    void buscarPorRg(){
        logger.info("Iniciando teste buscar por rg consulta.");
        consulta = service.salvar(consulta);
        List <Consulta> consultasPorRg = service.buscarPorRg("555");
        assertEquals("555",consultasPorRg.get(0).getPaciente().getRg());
        logger.info("Teste buscar por rg consulta finalizado.");

    }

    @Test
    void buscarPorMatricula(){
        logger.info("Iniciando teste buscar por matricula consulta.");
        consulta = service.salvar(consulta);
        List<Consulta> consultasPorMatricula = service.buscarPorMatricula(5555);
        assertEquals(5555, consultasPorMatricula.get(0).getDentista().getMatricula());
        logger.info("Teste buscar por matricula finalizado.");

    }

}