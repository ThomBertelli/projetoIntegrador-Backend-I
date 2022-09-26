package com.dh.clinicaOdonto;

import com.dh.clinicaOdonto.entity.Endereco;
import com.dh.clinicaOdonto.exception.ResourceNotFoundException;
import com.dh.clinicaOdonto.service.EnderecoService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class EnderecoServiceTest {

    Logger logger  = Logger.getLogger(EnderecoServiceTest.class);

    @Autowired
    EnderecoService service;

    static Endereco endereco;

    @BeforeAll
    static void doBefore() {
        endereco = new Endereco();
        endereco.setCidade("Taubaté");
        endereco.setEstado("SP");
        endereco.setNumero("160");
        endereco.setRua("Rua Claudino Velloso Borges");
    }

    @Test
    void salvar() {
        logger.info("Iniciando teste salvar endereço.");
        Endereco enderecoSalvo = new Endereco();
        service.salvar(endereco);
        Assertions.assertNotNull(endereco.getId());
        logger.info("Teste salvar endereço finalizado.");
    }

    @Test
    void buscarTodos() {
        logger.info("Iniciando teste buscar todos os endereço.");
        List Endereco = service.buscarTodos();
        Assertions.assertEquals(Endereco.isEmpty(), false);
        logger.info("Teste buscar todos endereços finalizado.");
    }

    @Test
    void alterar() throws ResourceNotFoundException {
        logger.info("Iniciando teste alterar endereço.");
        Endereco enderecoAlterado = new Endereco();
        service.salvar(endereco);
        service.buscaPorId(2L);
        endereco.setRua("Rua Antonio Delgado da Veiga");
        endereco.setNumero("10");
        Assertions.assertEquals("Rua Antonio Delgado da Veiga", endereco.getRua());
        Assertions.assertEquals("10", endereco.getNumero());
        logger.info("Teste alterar endereço finalizado.");
    }

    @Test
    void excluir() throws ResourceNotFoundException {
        logger.info("Iniciando teste de exclusão do endereço.");
        Endereco enderecoAlterado = new Endereco();
        service.salvar(endereco);
        service.excluir(1L);
        Assertions.assertNotEquals(0,endereco.getId());
        logger.info("Teste de exclusão do endereço finalizado.");
    }

    @Test
    void buscaPorId() throws ResourceNotFoundException {
        logger.info("Iniciando teste busca endereço por id.");
        Endereco enderecoAlterado = new Endereco();
        endereco = service.salvar(endereco);
        service.buscaPorId(4L);
        assertEquals(4L,endereco.getId());
        logger.info("Teste busca endereço por id finalizado.");
    }
}