package com.dh.clinicaOdonto.repository.impl;

import com.dh.clinicaOdonto.entity.Dentista;
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
import java.util.List;
import java.util.Optional;

@Repository
@Configuration

public class DentistaDAOH2 implements IDao<Dentista> {
    private ConfiguracaoJDBC configuracaoJDBC;

    final static Logger log = Logger.getLogger(String.valueOf(DentistaDAOH2.class));

    @Override
    public Dentista salvar(Dentista dentista) throws SQLException {
        log.info("Abrindo conexão");

        String SQLInsert = String.format("INSERT INTO dentista (nome, sobrenome, matricula)" +
                " VALUES ('%s','%s', '%s')", dentista.getNome(), dentista.getSobrenome(), dentista.getMatricula());
        Connection connection = null;
        try{
            log.info("Salvando Dentista: "+dentista.getNome());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/clinicaodonto;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(SQLInsert,Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet.next())
                dentista.setId(resultSet.getInt(1));

        }catch (Exception e){
            e.printStackTrace();
            log.error("Erro ao inserir dentista: "+ e.getMessage()); ;
        }finally {
            log.info("Fechando conexão");
            connection.close();
        }
        return dentista;
    }

    @Override
    public List<Dentista> buscarTodos() throws SQLException {
        log.debug("Abrindo uma conexão no banco");
        Connection connection = null;
        Statement stmt = null;
        String query = "SELECT * FROM dentista";
        List<Dentista> dentistas = new ArrayList<>();
        try {

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/clinicaodonto;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            stmt = connection.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            log.debug("Buscando todos os dentistas do banco");
            while (resultado.next()) {
                dentistas.add(criarObjetoDentista(resultado));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

            log.debug("Fechando a conexão no banco");
            stmt.close();
        }

        return dentistas;
    }

    @Override
    public void alterar(Dentista dentista) throws SQLException {

    }

    @Override
    public Optional<Dentista> buscarPorId(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public void excluir(int id) throws SQLException {

    }

    private Dentista criarObjetoDentista(ResultSet result) throws SQLException {

        Integer id = result.getInt("id");
        String nome = result.getString("nome");
        String sobrenome = result.getString("sobrenome");
        Integer matricula = result.getInt("matricula");
        return new Dentista(id, nome, sobrenome, matricula);

    }
}
