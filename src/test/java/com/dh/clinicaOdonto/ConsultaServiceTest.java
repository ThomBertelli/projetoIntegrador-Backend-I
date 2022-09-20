package com.dh.clinicaOdonto;

import com.dh.clinicaOdonto.entity.Consulta;
import com.dh.clinicaOdonto.service.ConsultaService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ConsultaServiceTest {

    @Autowired
    ConsultaService service;

    static Consulta consulta;


}