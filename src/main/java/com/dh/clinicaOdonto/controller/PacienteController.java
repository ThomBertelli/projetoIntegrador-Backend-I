package com.dh.clinicaOdonto.controller;

import com.dh.clinicaOdonto.dto.PacienteDTO;
import com.dh.clinicaOdonto.entity.Paciente;
import com.dh.clinicaOdonto.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService service;

    @PostMapping
    public Paciente salvaPaciente(@RequestBody Paciente paciente){return service.salvar(paciente);}

    @GetMapping
    public List<PacienteDTO> buscarTodos(){ return service.buscarTodos();}

    @RequestMapping(value= "/buscaId", method = RequestMethod.GET)
    public ResponseEntity buscaPorId(@RequestParam("id") Long id){
        ObjectMapper mapper = new ObjectMapper();
        Optional<Paciente> pacienteOptional = service.buscaPorId(id);
        if(pacienteOptional.isEmpty()){
            return new ResponseEntity( "Paciente não encontrado", HttpStatus.NOT_FOUND);
        }
        Paciente paciente = pacienteOptional.get();
        PacienteDTO pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);

        return new ResponseEntity(pacienteDTO, HttpStatus.OK);
    }

    @PatchMapping
    public void alterar(@RequestBody Paciente paciente){ service.alterar(paciente);}

    @DeleteMapping
    public void excluir(@RequestParam("id") Long id){service.excluir(id);}

}
