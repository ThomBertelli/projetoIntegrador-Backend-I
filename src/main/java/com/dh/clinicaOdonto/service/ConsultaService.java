package com.dh.clinicaOdonto.service;
import com.dh.clinicaOdonto.dto.ConsultaDTO;
import com.dh.clinicaOdonto.dto.DentistaDTO;
import com.dh.clinicaOdonto.entity.Consulta;
import com.dh.clinicaOdonto.entity.Dentista;
import com.dh.clinicaOdonto.exception.ResourceNotFoundException;
import com.dh.clinicaOdonto.repository.ConsultaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Consulta alterar(Consulta consulta)  {
        return repository.save(consulta);
    }


    public void excluir(Long id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao excluir consulta, id informado inexiste"));
        repository.deleteById(id);
    }

    public ConsultaDTO buscaPorId(Long id) throws ResourceNotFoundException{
        ObjectMapper mapper = new ObjectMapper();
        Optional<Consulta> consultaOptional = repository.findById(id);

        ConsultaDTO consultaDTO = null;
        try{
            Consulta consulta =  consultaOptional.get();
            consultaDTO = mapper.convertValue(consulta, ConsultaDTO.class);
        }catch (Exception ex){
            throw new ResourceNotFoundException("Erro ao buscar consulta, id da consulta não existe");
        }
        return consultaDTO;
    }


}
