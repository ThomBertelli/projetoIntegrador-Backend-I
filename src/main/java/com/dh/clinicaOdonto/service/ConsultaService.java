package com.dh.clinicaOdonto.service;

import com.dh.clinicaOdonto.dto.ConsultaDTO;
import com.dh.clinicaOdonto.entity.Consulta;
import com.dh.clinicaOdonto.entity.Dentista;
import com.dh.clinicaOdonto.dto.DentistaDTO;
import com.dh.clinicaOdonto.repository.ConsultaRepository;
import com.dh.clinicaOdonto.repository.DentistaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    ConsultaRepository repository;

    public Consulta salvar (Consulta consulta){
        return repository.save(consulta);
    }

    public List<ConsultaDTO> buscarTodos() {
        List<Consulta> listConsulta = repository.findAll();
        List<ConsultaDTO> listConsultaDTO = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        for (Consulta c : listConsulta){
            listConsultaDTO.add(mapper.convertValue(c, ConsultaDTO.class));
        }
        return listConsultaDTO;
    }

    public List<Consulta> buscarPorRg(String rg){

        return repository.findByPacienteRg(rg);
    }

    public List<Consulta> buscarPorMatricula(int matricula){

        return repository.findByDentistaMatricula(matricula);
    }

    public void alterar(Consulta consulta)  {
        repository.save(consulta);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Optional<Consulta> buscaPorId(Long id){
        return repository.findById(id);
    }


}
