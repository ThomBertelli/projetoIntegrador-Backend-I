package com.dh.clinicaOdonto.controller;

import com.dh.clinicaOdonto.dto.DentistaDTO;
import com.dh.clinicaOdonto.entity.Dentista;
import com.dh.clinicaOdonto.exception.ResourceNotFoundException;
import com.dh.clinicaOdonto.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentista")
public class DentistaController {
    @Autowired
    DentistaService service;

    @PostMapping
    public Dentista salvaDentista(@RequestBody Dentista dentista)  {
        return service.salvar(dentista);
    }

    @GetMapping
    public List<DentistaDTO> buscarTodos() {return service.buscarTodos();}

    @RequestMapping(value = "/buscaId", method = RequestMethod.GET)
    public ResponseEntity buscarPorId(@RequestParam("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity(service.buscaPorId(id), HttpStatus.OK);
    }

    @PatchMapping
    public void alterar(@RequestBody Dentista dentista) {
        service.alterar(dentista);
    }

    @DeleteMapping
    public void excluir(@RequestParam("id") Long id) throws ResourceNotFoundException {
        service.excluir(id);
    }
}
