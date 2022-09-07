package com.dh.clinicaOdonto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationContext;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "consultas")

    public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dentista" , referencedColumnName = "id")
    private Dentista dentista;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_paciente" , referencedColumnName = "id")
    private Paciente paciente;

    private Date data_consulta;


}
