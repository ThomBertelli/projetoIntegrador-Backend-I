package com.dh.clinicaOdonto.service;


import com.dh.clinicaOdonto.config.security.TokenService;
import com.dh.clinicaOdonto.dto.PacienteDTO;
import com.dh.clinicaOdonto.entity.Paciente;
import com.dh.clinicaOdonto.exception.ResourceNotFoundException;
import com.dh.clinicaOdonto.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    Logger logger  = Logger.getLogger(TokenService.class);

    @Autowired
    PacienteRepository repository;

    public Paciente salvar (Paciente paciente) {
        paciente.setDataCadastro(Timestamp.valueOf(LocalDateTime.now()));
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

    public void excluir(Long id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao excluir paciente, id informado não existe"));
        repository.deleteById(id);
    }

    public PacienteDTO buscaPorId(Long id) throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper();

        Optional<Paciente> produtoOptional = repository.findById(id);

        PacienteDTO pacienteDTO = null;
        try{
            logger.info("Paciente encontrado");

            //Aqui fazemos a conversão de Paciente para PacienteDTO usando Jayson
            Paciente paciente =  produtoOptional.get();
            pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);
        }catch (Exception ex){
            logger.info("Paciente não encontrado");
            throw new ResourceNotFoundException("Erro ao buscar paciente, id do paciente não existe");
        }
        return pacienteDTO;
    }
}