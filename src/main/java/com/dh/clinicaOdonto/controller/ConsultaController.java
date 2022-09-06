package com.dh.clinicaOdonto.controller;

import com.dh.clinicaOdonto.dto.ConsultaDTO;
import com.dh.clinicaOdonto.entity.Consulta;
import com.dh.clinicaOdonto.entity.Dentista;
import com.dh.clinicaOdonto.dto.DentistaDTO;
import com.dh.clinicaOdonto.service.ConsultaService;
import com.dh.clinicaOdonto.service.DentistaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consulta")

public class ConsultaController {

    @Autowired
    ConsultaService service;

    @PostMapping
    public Consulta salvarConsulta (@RequestBody Consulta consulta){
        return service.salvar(consulta);
    }

    @GetMapping
    public  List<ConsultaDTO> buscarTodos () throws SQLException{
        return service.buscarTodos();
    }

    @RequestMapping (value = "/buscarId", method = RequestMethod.GET)
    public ResponseEntity buscarPorId(@RequestParam("id") Long id){
        ObjectMapper mapper = new ObjectMapper();
        Optional<Consulta> consultaOptional = service.buscaPorId(id);
        if(consultaOptional.isEmpty()){
           return new ResponseEntity ("Consulta não foi encontrada", HttpStatus.NOT_FOUND);
        }
        Consulta consulta = consultaOptional.get();
        ConsultaDTO consultaDTO = mapper.convertValue(consulta, ConsultaDTO.class);

        return new ResponseEntity(consultaDTO, HttpStatus.OK);
    }

    @PatchMapping
    public void alterar (@RequestBody Consulta consulta){
        service.alterar(consulta);
    }

    @DeleteMapping
    public void excluir (@RequestParam("id") Long id) {
        service.excluir(id);
    }

}
