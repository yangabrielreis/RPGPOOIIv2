package com.trabalhojava.sistemarpg.dao;

import com.trabalhojava.sistemarpg.model.Raca;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RacaDBDAO implements RacaDAO, IConst {
    private String sql;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    public RacaDBDAO() {}

    private void open() throws SQLException {
        connection = Conexao.getConexao("jdbc:postgresql://localhost:5432/sistemarpg", "postgres", "postgres");
    }

    private void close() throws SQLException {
        connection.close();
    }

    public void insere(Raca raca) throws SQLException {
        open();
        sql = "INSERT INTO raca(racaId, nomeRaca, descricao, forca, destreza, constituicao, inteligencia, sabedoria, carisma, sistemaId) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, raca.getRacaId());
        statement.setString(2, raca.getNomeRaca());
        statement.setString(3, raca.getDescricao());
        statement.setInt(4, raca.getForca());
        statement.setInt(5, raca.getDestreza());
        statement.setInt(6, raca.getConstituicao());
        statement.setInt(7, raca.getInteligencia());
        statement.setInt(8, raca.getSabedoria());
        statement.setInt(9, raca.getCarisma());
        statement.setInt(10, raca.getSistema().getSistemaId());
        statement.executeUpdate();
        close();
    }

    public void atualizar(Raca raca) throws SQLException {
        open();
        sql = "UPDATE raca SET nomeRaca=?, descricao=?, forca=?, destreza=?, constituicao=?, inteligencia=?, sabedoria=?, carisma=?, sistemaId=? WHERE racaId=?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, raca.getNomeRaca());
        statement.setString(2, raca.getDescricao());
        statement.setInt(3, raca.getForca());
        statement.setInt(4, raca.getDestreza());
        statement.setInt(5, raca.getConstituicao());
        statement.setInt(6, raca.getInteligencia());
        statement.setInt(7, raca.getSabedoria());
        statement.setInt(8, raca.getCarisma());
        statement.setInt(9, raca.getSistema().getSistemaId());
        statement.setInt(10, raca.getRacaId());
        statement.executeUpdate();
        close();
    }

    public void remover(Raca raca) throws SQLException {
        open();
        sql = "DELETE FROM raca WHERE racaId=?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1,raca.getRacaId());
        statement.executeUpdate();
        close();
    }

    public Raca buscarPorCodigo(int racaId) throws SQLException {
        open();
        sql = "SELECT * FROM raca WHERE racaId=?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, racaId);
        result = statement.executeQuery();
        
        if (result.next()) {
            Raca raca = new Raca();
            raca.setRacaId(result.getInt("racaId"));
            raca.setNomeRaca(result.getString("nomeRaca"));
            raca.setDescricao(result.getString("descricao"));
            raca.setForca(result.getInt("forca"));
            raca.setDestreza(result.getInt("destreza"));
            raca.setConstituicao(result.getInt("constituicao"));
            raca.setInteligencia(result.getInt("inteligencia"));
            raca.setSabedoria(result.getInt("sabedoria"));
            raca.setCarisma(result.getInt("carisma"));
            close();
            return raca;
        }
        else {
            close();
            return null;
        }
    }

        public Raca buscaPorNome(String nomeRaca) throws SQLException {
            open();
            sql = "SELECT * FROM raca WHERE nomeRaca LIKE ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + nomeRaca + "%");
            result = statement.executeQuery();
            
            if (result.next()) {
                Raca raca = new Raca();
                raca.setRacaId(result.getInt("racaId"));
                raca.setNomeRaca(result.getString("nomeRaca"));
                raca.setDescricao(result.getString("descricao"));
                raca.setForca(result.getInt("forca"));
                raca.setDestreza(result.getInt("destreza"));
                raca.setConstituicao(result.getInt("constituicao"));
                raca.setInteligencia(result.getInt("inteligencia"));
                raca.setSabedoria(result.getInt("sabedoria"));
                raca.setCarisma(result.getInt("carisma"));
                raca.setSistema(new SistemaDBDAO().buscaPorCodigo(result.getInt("sistemaId")));
                close();
                return raca;
            } else {
                close();
                return null;
            }
        }
       

    public List<Raca> listar() throws SQLException {
        open();
        sql = "SELECT * FROM raca";
        statement = connection.prepareStatement(sql);
        result = statement.executeQuery();
        ArrayList<Raca> racas = new ArrayList<>();
        SistemaDBDAO sistemaDB = new SistemaDBDAO();
        while (result.next()) {
            Raca raca = new Raca();
            raca.setRacaId(result.getInt("racaId"));
            raca.setNomeRaca(result.getString("nomeRaca"));
            raca.setDescricao(result.getString("descricao"));
            raca.setForca(result.getInt("forca"));
            raca.setDestreza(result.getInt("destreza"));
            raca.setConstituicao(result.getInt("constituicao"));
            raca.setInteligencia(result.getInt("inteligencia"));
            raca.setSabedoria(result.getInt("sabedoria"));
            raca.setCarisma(result.getInt("carisma"));
            raca.setSistema(sistemaDB.buscaPorCodigo(result.getInt("sistemaId")));
            racas.add(raca);
        }
        close();
        return racas;
    }
}
