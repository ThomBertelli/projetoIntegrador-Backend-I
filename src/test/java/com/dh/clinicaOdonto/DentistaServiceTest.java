package com.dh.clinicaOdonto;

import com.dh.clinicaOdonto.dto.DentistaDTO;
import com.dh.clinicaOdonto.entity.Dentista;
import com.dh.clinicaOdonto.exception.ResourceNotFoundException;
import com.dh.clinicaOdonto.service.DentistaService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class DentistaServiceTest {
    Logger logger  = Logger.getLogger(DentistaServiceTest.class);

    @Autowired
    DentistaService service;
    static Dentista dentista;

    @BeforeAll
    static void doBefore() {
        dentista = new Dentista();
        dentista.setId(1L);
        dentista.setMatricula(5555);
        dentista.setNome("Carlos");
        dentista.setSobrenome("Correa");
    }

    @Test
    void salvar() {
        logger.info("Iniciando teste salvar dentista.");
        Dentista dentistaSalva = new Dentista();
        service.salvar(dentista);
        Assertions.assertNotNull(dentista.getId());
        logger.info("Teste salvar dentista finalizado.");

    }

    @Test
    void buscarTodos() {
        logger.info("Iniciando teste busca de todos dentista.");
        List Dentista = service.buscarTodos();
        Assertions.assertEquals(Dentista.isEmpty(), false);
        logger.info("Finalizado teste buscar todos dentista.");
    }

    @Test
    void alterar() throws ResourceNotFoundException {
        logger.info("Iniciando teste alterar dentista.");
        DentistaDTO dentistaAlterar = service.buscaPorId(1L);
        dentista.setNome("Harry");
        Dentista dentistaAlterado = service.alterar(dentista);
        logger.info("Finalizando teste alterar dentista.");
    }

    @Test
    void excluir() throws ResourceNotFoundException {
        logger.info("Iniciando teste de exclusão do dentista.");
        Dentista dentistaAlterado = new Dentista();
        service.salvar(dentista);
        service.excluir(1L);
        Assertions.assertNotEquals(0,dentista.getId());
        logger.info("Teste de exclusão do Dentista finalizado.");
    }

    @Test
    void buscaPorId() throws ResourceNotFoundException {
        logger.info("Iniciando teste busca dentista por id.");
        Dentista destistaAlterado = new Dentista();
        dentista = service.salvar(dentista);
        service.buscaPorId(4L);
        assertEquals(4L,dentista.getId());
        logger.info("Teste busca dentista por id finalizado.");
    }
}