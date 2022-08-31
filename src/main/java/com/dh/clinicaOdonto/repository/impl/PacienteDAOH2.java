package com.dh.clinicaOdonto.repository.impl;

import com.dh.clinicaOdonto.entity.Endereco;
import com.dh.clinicaOdonto.entity.Paciente;
import com.dh.clinicaOdonto.repository.ConfiguracaoJDBC;
import com.dh.clinicaOdonto.repository.IDao;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Configuration
public class PacienteDAOH2 implements IDao<Paciente>

    {
        private ConfiguracaoJDBC configuracaoJDBC;

        private EnderecoDAOH2 enderecoDAOH2;

        final static Logger log = Logger.getLogger(String.valueOf(PacienteDAOH2.class));

        public PacienteDAOH2(EnderecoDAOH2 enderecoDAOH2) {
            this.enderecoDAOH2 = enderecoDAOH2;
        }

        @Override
        public Paciente salvar(Paciente paciente) throws SQLException {
        log.info("Abrindo conexão");

        String SQLInsert = String.format("INSERT INTO paciente (nome, sobrenome, rg, data_cadastro, endereco_id)" +
                " VALUES ('%s','%s', '%s','%s', '%s')", paciente.getNome(), paciente.getSobrenome(), paciente.getRg(), paciente.getDataCadastro(), paciente.getEndereco().getId());
        Connection connection = null;
        try{
            log.info("Salvando Paciente: "+paciente.getNome());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/clinicaodonto;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(SQLInsert,Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet.next())
                paciente.setId(resultSet.getInt(1));

        }catch (Exception e){
            e.printStackTrace();
            log.error("Erro ao inserir paciente: "+ e.getMessage()); ;
        }finally {
            log.info("Fechando conexão");
            connection.close();
        }
        return paciente;
    }

        @Override
        public List<Paciente> buscarTodos() throws SQLException {
            log.debug("Abrindo uma conexão no banco");
            Connection connection = null;
            Statement statement = null;
            String query = "SELECT * FROM paciente";
            List<Paciente> pacientes = new ArrayList<>();
            try {

                configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/clinicaodonto;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
                connection = configuracaoJDBC.getConnection();
                statement = connection.createStatement();
                ResultSet resultado = statement.executeQuery(query);

                log.debug("Buscando todos os pacientes do banco");
                while (resultado.next()) {
                    pacientes.add(criarObjetoPaciente(resultado));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {

                log.debug("Fechando a conexão no banco");
                statement.close();
            }

            return pacientes;
        }

        @Override
        public void alterar(Paciente paciente) throws SQLException {

        }

        @Override
        public Optional<Paciente> buscarPorId(int id) throws SQLException {
            return Optional.empty();
        }

        @Override
        public void excluir(int id) throws SQLException {

        }

        private Paciente criarObjetoPaciente(ResultSet result) throws SQLException {

            Integer idPaciente = result.getInt("id");
            String nome = result.getString("nome");
            String sobrenome = result.getString("sobrenome");
            String rg = result.getString("rg");
            Date dataCadastro = result.getDate("data_cadastro");
            Endereco endereco = enderecoDAOH2.buscar(result.getInt("endereco_id")).orElse(null);
            return new Paciente(idPaciente, nome, sobrenome, endereco, rg, dataCadastro);


        }
    }
