package com.dh.clinicaOdonto.controller;

import com.dh.clinicaOdonto.entity.Dentista;
import com.dh.clinicaOdonto.dto.DentistaDTO;
import com.dh.clinicaOdonto.service.DentistaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentista")
public class DentistaController {
    @Autowired
    DentistaService service;

    @PostMapping
    public Dentista salvaDentista(@RequestBody Dentista dentista) throws SQLException {
        return service.salvar(dentista);
    }

    @GetMapping
    public List<DentistaDTO> buscarTodos() throws SQLException {
        return service.buscarTodos();
    }
    @RequestMapping(value = "/buscaId", method = RequestMethod.GET)
    public DentistaDTO buscarPorId(@RequestParam("id") int id) throws SQLException {
        ObjectMapper mapper = new ObjectMapper();
        Optional<Dentista> produtoOptional = service.buscaPorId(id);
        if(produtoOptional.isEmpty()){
            return new DentistaDTO();
        }
        Dentista dentista =  produtoOptional.get();
        DentistaDTO dentistaDTO = mapper.convertValue(dentista, DentistaDTO.class);

        return dentistaDTO;

    }

    @PatchMapping
    public void alterar(@RequestBody Dentista dentista) throws SQLException {
        service.alterar(dentista);
    }

    @DeleteMapping
    public void excluir(@RequestParam("id") int id) throws SQLException {
        service.excluir(id);
    }
}
