package com.dh.clinicaOdonto.service;

import com.dh.clinicaOdonto.dto.DentistaDTO;
import com.dh.clinicaOdonto.entity.Dentista;
import com.dh.clinicaOdonto.exception.ResourceNotFoundException;
import com.dh.clinicaOdonto.repository.DentistaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void excluir(Long id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao excluir Dentista, id informado não existe"));
        repository.deleteById(id);
    }


    public DentistaDTO buscaPorId(Long id) throws ResourceNotFoundException{
        ObjectMapper mapper = new ObjectMapper();
        Optional<Dentista> dentistaOptional = repository.findById(id);

        DentistaDTO dentistaDTO = null;
        try{
            //Aqui fazemos a conversão de Dentista para DentistaDTO usando Json
            Dentista dentista =  dentistaOptional.get();
            dentistaDTO = mapper.convertValue(dentista, DentistaDTO.class);
        }catch (Exception ex){
            //throw new ResourceNotFoundException("Erro ao buscar dentista, id do dentista não existe");
        }
        return dentistaDTO;
    }
}