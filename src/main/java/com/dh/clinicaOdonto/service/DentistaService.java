package com.dh.clinicaOdonto.service;

import com.dh.clinicaOdonto.repository.IDao;
import com.dh.clinicaOdonto.entity.Dentista;
import com.dh.clinicaOdonto.dto.DentistaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

    @Autowired
    IDao<Dentista> dentistaIDaoH2;

    public Dentista salvar(Dentista dentista) throws SQLException {
        return dentistaIDaoH2.salvar(dentista);
    }

    public List<DentistaDTO> buscarTodos() throws SQLException {
        List<Dentista> listDentista = dentistaIDaoH2.buscarTodos();
        List<DentistaDTO> listDentistaDTO = new ArrayList<>();

        for (Dentista d : listDentista){
            listDentistaDTO.add(new DentistaDTO(d));
        }
        return listDentistaDTO;
    }

    public void alterar(Dentista dentista) throws SQLException {
        dentistaIDaoH2.alterar(dentista);
    }

    public void excluir(int id) throws SQLException {
        dentistaIDaoH2.excluir(id);
    }

    public Optional<Dentista> buscaPorId(int id) throws SQLException {
        return dentistaIDaoH2.buscarPorId(id);
    }
}
