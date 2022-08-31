package com.dh.clinicaOdonto.repository.impl;

import com.dh.clinicaOdonto.entity.Endereco;
import com.dh.clinicaOdonto.repository.ConfiguracaoJDBC;
import com.dh.clinicaOdonto.repository.IDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class EnderecoDAOH2 implements IDao<Endereco> {
    private ConfiguracaoJDBC configuracaoJDBC;

    final static Logger log = Logger.getLogger(String.valueOf(EnderecoDAOH2.class));

    @Override
    public Endereco salvar(Endereco endereco)  throws SQLException {
         String SQLInsert = String.format("INSERT INTO endereco (rua ,numero, cidade, estado) " +
                        "VALUES ('%s','%s','%s','%s')", endereco.getRua(),
                endereco.getNumero(), endereco.getCidade(), endereco.getEstado());
        Connection connection = null;
        try{
            log.info("Salvando Endereço!");
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/clinicaodonto;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(SQLInsert,Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet.next())
                endereco.setId(resultSet.getInt(1));
        }catch (Exception e){
            e.printStackTrace();
            log.error("Erro ao inserir endereço: "+ e.getMessage()); ;
        }finally {
            log.info("Fechando conexão");
            connection.close();
        }
        return endereco;
    }
    public Optional<Endereco> buscar(Integer id) throws SQLException {
        String SQLInsert = String.format("SELECT id, rua, numero, cidade, estado FROM " +
                "ENDERECO WHERE id = '%s'", id);
        Connection connection = null;
        Endereco endereco = null;
        try{
            log.info("Salvando Endereço!");
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/clinicaodonto;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(SQLInsert);
            while (resultado.next()) {
                endereco = criarObjetoEndereco(resultado);
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("Erro ao inserir endereço: "+ e.getMessage()); ;
        }finally {
            log.info("Fechando conexão");
            connection.close();
        }
        return endereco != null ? Optional.of(endereco) : Optional.empty();
    }

    @Override
    public List<Endereco> buscarTodos() throws SQLException {
        return null;
    }

    @Override
    public void alterar(Endereco endereco) throws SQLException {

    }

    @Override
    public Optional<Endereco> buscarPorId(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public void excluir(int id) throws SQLException {

    }


    private Endereco criarObjetoEndereco(ResultSet resultado) throws SQLException {
        Integer id = resultado.getInt("id");
        String rua = resultado.getString("rua");
        String numero = resultado.getString("numero");
        String cidade = resultado.getString("cidade");
        String estado = resultado.getString("estado");
        return new Endereco(id, rua, numero, cidade, estado);

    }
}
