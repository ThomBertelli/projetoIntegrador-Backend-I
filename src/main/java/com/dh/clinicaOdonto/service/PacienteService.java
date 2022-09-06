package com.dh.clinicaOdonto.service;


import com.dh.clinicaOdonto.entity.Paciente;
import com.dh.clinicaOdonto.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository repository;

    public Paciente salvar (Paciente paciente) {
        return  repository.save(paciente);
    }

    public List<PacienteDTO> buscarTodos(){
        List<Paciente> listPaciente = repository.findAll();
        List<PacienteDTO> listPacienteDTO = new ArrayList<>();

        for (Paciente e : listPaciente){
            listPacienteDTO.add(new PacienteDTO(e));
        }

        return listPacienteDTO;

    }

    public void alterar(Paciente paciente){repository.save(paciente);}

    public void excluir(Long id){repository.deleteById(id);}

    public Optional<Paciente> buscaPorId(Long id) {return repository.findById(id);}

}
