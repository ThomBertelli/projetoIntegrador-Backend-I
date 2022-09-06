package com.dh.clinicaOdonto.service;

import com.dh.clinicaOdonto.entity.Dentista;
import com.dh.clinicaOdonto.dto.DentistaDTO;
import com.dh.clinicaOdonto.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

    @Autowired
    DentistaRepository repository;

    public Dentista salvar(Dentista dentista) {
        return repository.save(dentista);
    }

    public List<DentistaDTO> buscarTodos() {
        List<Dentista> listDentista = repository.findAll();
        List<DentistaDTO> listDentistaDTO = new ArrayList<>();

        for (Dentista d : listDentista){
            listDentistaDTO.add(new DentistaDTO(d));
        }
        return listDentistaDTO;
    }

    public void alterar(Dentista dentista)  {
        repository.save(dentista);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Optional<Dentista> buscaPorId(Long id){
        return repository.findById(id);
    }
}
