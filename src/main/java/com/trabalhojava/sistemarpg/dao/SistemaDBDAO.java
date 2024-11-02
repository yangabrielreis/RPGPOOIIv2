package com.trabalhojava.sistemarpg.dao;

import com.trabalhojava.sistemarpg.model.Sistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SistemaDBDAO implements SistemaDAO, IConst {
    private String sql;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    public SistemaDBDAO() {}

    public void open() throws SQLException {
        connection = Conexao.getConexao("jdbc:postgresql://localhost:5432/sistemarpg", "postgres", "postgres");
    }

    private void close() throws SQLException {
        connection.close();
    }

    public void insere(Sistema sistema) throws SQLException {
        open();
        sql = "INSERT INTO sistema(sistemaId,nome) VALUES (?, ?)";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, sistema.getSistemaId());
        statement.setString(2, sistema.getNome());
        statement.executeUpdate();
        close();
    }

    public void atualizar(Sistema sistema) throws SQLException {
        open();
        sql = "UPDATE sistema SET nome=? WHERE sistemaId=?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, sistema.getNome());
        statement.setInt(2, sistema.getSistemaId());
        statement.executeUpdate();
        close();
    }

    public void remover(Sistema sistema) throws SQLException {
        open();
        sql = "DELETE FROM sistema WHERE sistemaId=?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1,sistema.getSistemaId());
        statement.executeUpdate();
        close();
    }

    public Sistema buscaPorCodigo(int sistemaId) throws SQLException {
        open();
        sql = "SELECT * FROM sistema WHERE sistemaId=?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, sistemaId);
        result = statement.executeQuery();
        if (result.next()) {
            Sistema sistema = new Sistema();
            sistema.setSistemaId(result.getInt("sistemaId"));
            sistema.setNome(result.getString("nome"));
            close();
            return sistema;
        }
        else {
            close();
            return null;
        }
    }

    public List<Sistema> listar() throws SQLException {
        open();
        sql = "SELECT * FROM sistema";
        statement = connection.prepareStatement(sql);
        result = statement.executeQuery();
        ArrayList<Sistema> sistemas = new ArrayList<>();

        while (result.next()) {
            Sistema sistema = new Sistema();
            sistema.setSistemaId(result.getInt("sistemaId"));
            sistema.setNome(result.getString("nome"));
            sistemas.add(sistema);
        }
        close();
        return sistemas;
    }
}
