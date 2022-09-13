package com.dh.clinicaOdonto.repository;

import com.dh.clinicaOdonto.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByPacienteRg(String rg);

    List<Consulta> findByDentistaMatricula(int matricula);
}
