package com.dh.clinicaOdonto.service;

import com.dh.clinicaOdonto.dto.EnderecoDTO;
import com.dh.clinicaOdonto.entity.Endereco;
import com.dh.clinicaOdonto.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
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

    public void excluir(Long id) {
       repository.deleteById(id);
    }

    public Optional<Endereco> buscaPorId(Long id){
        return repository.findById(id);
    }
    
}
