package com.dh.clinicaOdonto.controller;

import com.dh.clinicaOdonto.entity.Dentista;
import com.dh.clinicaOdonto.dto.DentistaDTO;
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
@RequestMapping("/dentista")
public class DentistaController {
    @Autowired
    DentistaService service;

    @PostMapping
    public Dentista salvaDentista(@RequestBody Dentista dentista)  {
        return service.salvar(dentista);
    }

    @GetMapping
    public List<DentistaDTO> buscarTodos() throws SQLException {
        return service.buscarTodos();
    }
    @RequestMapping(value = "/buscaId", method = RequestMethod.GET)
    public ResponseEntity buscarPorId(@RequestParam("id") Long id)  {
        ObjectMapper mapper = new ObjectMapper();
        Optional<Dentista> dentistaOptional = service.buscaPorId(id);
        if(dentistaOptional.isEmpty()){
            return new ResponseEntity("Dentista não foi encontrado", HttpStatus.NOT_FOUND);
        }
        Dentista dentista =  dentistaOptional.get();
        DentistaDTO dentistaDTO = mapper.convertValue(dentista, DentistaDTO.class);

        return new ResponseEntity(dentistaDTO, HttpStatus.OK);

    }

    @PatchMapping
    public void alterar(@RequestBody Dentista dentista) {
        service.alterar(dentista);
    }

    @DeleteMapping
    public void excluir(@RequestParam("id") Long id) {
        service.excluir(id);
    }
}
