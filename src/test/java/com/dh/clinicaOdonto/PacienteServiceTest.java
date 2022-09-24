package com.dh.clinicaOdonto;

import com.dh.clinicaOdonto.dto.PacienteDTO;
import com.dh.clinicaOdonto.entity.Endereco;
import com.dh.clinicaOdonto.entity.Paciente;
import com.dh.clinicaOdonto.exception.ResourceNotFoundException;
import com.dh.clinicaOdonto.service.PacienteService;
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
import java.util.Optional;

@SpringBootTest

@Transactional
public class PacienteServiceTest {

    Logger logger  = Logger.getLogger(ConsultaServiceTest.class);

    @Autowired
    PacienteService pacienteService;

    static Paciente pacienteTest;

    static PacienteDTO pacienteDTOTest;
    static Endereco enderecoTestePaciente;


    @BeforeAll
    static void doBefore() {

        enderecoTestePaciente = new Endereco();
        enderecoTestePaciente.setCidade("Lages");
        enderecoTestePaciente.setEstado("SC");
        enderecoTestePaciente.setNumero("1234");
        enderecoTestePaciente.setRua("Av Dom Pedro");

        pacienteTest = new Paciente();
        pacienteTest.setNome("Abigail");
        pacienteTest.setEndereco(enderecoTestePaciente);
        pacienteTest.setSobrenome("Gata");
        pacienteTest.setRg("555");

        Timestamp data = Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2022, 12, 7), LocalTime.of(8, 45, 0)));

        pacienteDTOTest = new PacienteDTO();
        pacienteDTOTest.setNomeCompleto("Joao Silva");
        pacienteDTOTest.setDataCadastro(data);
        pacienteDTOTest.setEndereco(enderecoTestePaciente);
        pacienteDTOTest.setRg("666");

    }


    @Test
    void salvar() {
        logger.info("Iniciando teste salvar paciente");
        Paciente salvarpaciente = new Paciente();
        salvarpaciente = pacienteService.salvar(pacienteTest);

        Assertions.assertNotNull(salvarpaciente.getId());
        logger.info("Teste salvar paciente finalizado");
    }

    @Test
    void buscarTodos() {
        logger.info("Iniciando teste buscar todos");
        Paciente buscarPacientes = new Paciente();
        buscarPacientes = pacienteService.salvar(pacienteTest);

        List<PacienteDTO> resultado = pacienteService.buscarTodos();
        Assertions.assertFalse(resultado.size()>1);
        logger.info("Teste buscar todos finalizado");
    }

    @Test
    void alterar () throws ResourceNotFoundException {
        logger.info("Teste alterar nome iniciado");
        Paciente pacienteAlterado = new Paciente();
        pacienteService.salvar(pacienteTest);
        pacienteService.buscaPorId(1L);
        pacienteTest.setNome("Teste Nome");
        Assertions.assertEquals("Teste Nome", pacienteTest.getNome());
        logger.info("Teste alterar nome finalizado");

    }

    @Test
    void excluir() throws ResourceNotFoundException {
        logger.info("Iniciando teste excluir paciente");
        // Paciente excluirPaciente = new Paciente();
        pacienteService.salvar(pacienteTest);
        pacienteService.excluir(1L);
        Assertions.assertNotEquals(0,pacienteTest.getId());
        logger.info("Teste de exclusão de paciente finalizado.");
    }

     @Test
     void buscaPorId() throws ResourceNotFoundException {
         logger.info("Iniciando teste busca por id consulta.");
       pacienteTest = pacienteService.salvar(pacienteTest);
          PacienteDTO pacienteDTO = pacienteService.buscaPorId(1L);
        Assertions.assertEquals(1L, pacienteTest.getId());
         logger.info("Teste busca por id consulta finalizado.");

     }
}
