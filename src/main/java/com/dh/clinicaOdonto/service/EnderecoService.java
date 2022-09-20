package com.dh.clinicaOdonto.service;

import com.dh.clinicaOdonto.dto.EnderecoDTO;
import com.dh.clinicaOdonto.entity.Endereco;
import com.dh.clinicaOdonto.exception.ResourceNotFoundException;
import com.dh.clinicaOdonto.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository repository;

    public Endereco salvar(Endereco endereco)  {
        return repository.save(endereco);
    }

    public List<EnderecoDTO> buscarTodos()  {
        List<Endereco> listEndereco = repository.findAll();
        List<EnderecoDTO> listEnderecoDTO = new ArrayList<>();

        for (Endereco e : listEndereco){
            listEnderecoDTO.add(new EnderecoDTO(e));
        }
        return listEnderecoDTO;
    }

    public void alterar(Endereco endereco) {
        repository.save(endereco);
    }

    public void excluir(Long id) throws ResourceNotFoundException {

        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao excluir produto, id informado não existe"));
        repository.deleteById(id);
    }

    public EnderecoDTO buscaPorId(Long id)throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper();

        Optional<Endereco> enderecoOptional = repository.findById(id);

        EnderecoDTO enderecoDTO = null;
        try{
            Endereco endereco =  enderecoOptional.get();
            enderecoDTO = mapper.convertValue(endereco, EnderecoDTO.class);
        }catch (Exception ex){
            throw new ResourceNotFoundException("Erro ao buscar endereço, id do endereço não existe");
        }

        return enderecoDTO;
    }
    
}
