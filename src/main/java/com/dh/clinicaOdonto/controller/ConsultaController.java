package com.dh.clinicaOdonto.controller;

import com.dh.clinicaOdonto.dto.ConsultaDTO;
import com.dh.clinicaOdonto.entity.Consulta;
import com.dh.clinicaOdonto.entity.Dentista;
import com.dh.clinicaOdonto.entity.Paciente;
import com.dh.clinicaOdonto.exception.ResourceNotFoundException;
import com.dh.clinicaOdonto.exception.ValidationErrorException;
import com.dh.clinicaOdonto.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/consulta")

public class ConsultaController {

    @Autowired
    ConsultaService service;

    @PostMapping
    public ResponseEntity salvar(@RequestBody Consulta consulta) throws ValidationErrorException {
        Consulta consultaSalva = service.salvar(consulta);
        if(consultaSalva == null){
            return new ResponseEntity("Erro ao salvar consulta",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(consultaSalva,HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity buscarTodos(){
        List<ConsultaDTO> listConsulta = service.buscarTodos();
        if(listConsulta.isEmpty())return new ResponseEntity("Nenhuma consulta encontrada", HttpStatus.NOT_FOUND);
        return new ResponseEntity(listConsulta, HttpStatus.OK);
    }

    @RequestMapping(value = "/buscaId", method = RequestMethod.GET)
    public ResponseEntity buscarPorId(@RequestParam("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity(service.buscaPorId(id), HttpStatus.OK);
    }

    @GetMapping(path = "/buscaPorPaciente")
    public ResponseEntity buscaPorPaciente(@RequestParam("rg") String rg){
        List<Consulta> listConsulta = service.buscarPorRg(rg);
        if(listConsulta.isEmpty()){
            return new ResponseEntity("Nenhuma consulta encontrada para o paciente: " + Paciente.class.getName(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(listConsulta,HttpStatus.OK);
    }
    @GetMapping(path = "/buscaPorDentista")
    public ResponseEntity buscaPorDentista(@RequestParam("matricula") int matricula){
        List<Consulta> listConsulta = service.buscarPorMatricula(matricula);
        if(listConsulta.isEmpty()){
            return new ResponseEntity("Nenhuma consulta encontrada para o dentista: " + Dentista.class.getName(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(listConsulta,HttpStatus.OK);
    }

    @PatchMapping
    public void alterar (@RequestBody Consulta consulta){
        service.alterar(consulta);
    }

    @DeleteMapping
       public void excluir(@RequestParam("id") Long id) throws ResourceNotFoundException {
        service.excluir(id);
    }

}
