package com.dh.clinicaOdonto.controller;

import com.dh.clinicaOdonto.dto.EnderecoDTO;
import com.dh.clinicaOdonto.entity.Endereco;
import com.dh.clinicaOdonto.exception.ResourceNotFoundException;
import com.dh.clinicaOdonto.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @PostMapping
    public Endereco salvaEndereco(@RequestBody Endereco endereco)  {
        return service.salvar(endereco);
    }

    @GetMapping
    public List<EnderecoDTO> buscarTodos()  {
        return service.buscarTodos();
    }

    @RequestMapping(value = "/buscaId", method = RequestMethod.GET)
    public ResponseEntity buscarPorId(@RequestParam("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity(service.buscaPorId(id), HttpStatus.OK);
    }

    @PatchMapping
    public void alterar(@RequestBody Endereco endereco)  {
        service.alterar(endereco);
    }

    @DeleteMapping
    public void excluir(@RequestParam("id") Long id) throws ResourceNotFoundException {
        service.excluir(id);
    }

}
