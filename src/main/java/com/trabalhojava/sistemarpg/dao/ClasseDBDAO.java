package com.trabalhojava.sistemarpg.dao;

import com.trabalhojava.sistemarpg.model.Classe;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClasseDBDAO implements ClasseDAO, IConst{
    private String sql;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    public ClasseDBDAO() {}

    private void open() throws SQLException {
        connection = Conexao.getConexao("jdbc:postgresql://localhost:5432/sistemarpg", "postgres", "postgres");
    }

    private void close() throws SQLException {
        connection.close();
    }
    public void insere(Classe classe) throws SQLException {
        open();
        sql = "INSERT INTO classe(classeId, nomeClasse, descricao, hpInicial, hpNivel, nivelMax, sistemaId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(sql);
        statement.setInt(1,classe.getClasseId());
        statement.setString(2,classe.getNomeClasse());
        statement.setString(3,classe.getDescricao());
        statement.setInt(4,classe.getHpInicial());
        statement.setInt(5,classe.getHpNivel());
        statement.setInt(6,classe.getNivelMax());
        statement.setInt(7,classe.getSistema().getSistemaId());
        statement.executeUpdate();
        close();
    }

    public void atualizar(Classe classe) throws SQLException {
        open();
        sql = "UPDATE classe SET nomeClasse=?, descricao=?, hpInicial=?, hpNivel=?, nivelMax=?, sistemaId=? WHERE classeId=?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, classe.getNomeClasse());
        statement.setString(2, classe.getDescricao());
        statement.setInt(3, classe.getHpInicial());
        statement.setInt(4, classe.getHpNivel());
        statement.setInt(5, classe.getNivelMax());
        statement.setInt(6, classe.getSistema().getSistemaId());
        statement.setInt(7, classe.getClasseId());
        statement.executeUpdate();
        close();
    }

    public void remover(Classe classe) throws SQLException {
        open();
        sql = "DELETE FROM classe WHERE classeId=?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1,classe.getClasseId());
        statement.executeUpdate();
        close();
    }

    public Classe buscaPorCodigo(int classeId) throws SQLException {
        open();
        sql = "SELECT * FROM classe WHERE classeId=?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, classeId);
        result = statement.executeQuery();
        
        if (result.next()) {
            Classe classe = new Classe();
            classe.setClasseId(result.getInt("classeId"));
            classe.setNomeClasse(result.getString("nomeClasse"));
            classe.setDescricao(result.getString("descricao"));
            classe.setHpInicial(result.getInt("hpInicial"));
            classe.setHpNivel(result.getInt("hpNivel"));
            classe.setNivelMax(result.getInt("nivelMax"));
            close();
            return classe;
        }
        else {
            close();
            return null;
        }
    }

    
    public Classe buscaPorNome(String nomeClasse) throws SQLException {
        open();
        sql = "SELECT * FROM classe WHERE nomeClasse = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, nomeClasse);
        result = statement.executeQuery();
        
        if (result.next()) {
            Classe classe = new Classe();
            classe.setClasseId(result.getInt("classeId"));
            classe.setNomeClasse(result.getString("nomeClasse"));
            classe.setDescricao(result.getString("descricao"));
            classe.setHpInicial(result.getInt("hpInicial"));
            classe.setHpNivel(result.getInt("hpNivel"));
            classe.setNivelMax(result.getInt("nivelMax"));
            SistemaDBDAO sistemaDB = new SistemaDBDAO();
            classe.setSistema(sistemaDB.buscaPorCodigo(result.getInt("sistemaId")));
            close();
            return classe;
        } else {
            close();
            return null;
        }
    }
    public List<Classe> listar() throws SQLException {
        open();
        sql = "SELECT * FROM classe";
        statement = connection.prepareStatement(sql);
        result = statement.executeQuery();
        ArrayList<Classe> classes = new ArrayList<>();
        SistemaDBDAO sistemaDB = new SistemaDBDAO();
        while (result.next()) {
            Classe classe = new Classe();
            classe.setClasseId(result.getInt("classeId"));
            classe.setNomeClasse(result.getString("nomeClasse"));
            classe.setDescricao(result.getString("descricao"));
            classe.setHpInicial(result.getInt("hpInicial"));
            classe.setHpNivel(result.getInt("hpNivel"));
            classe.setNivelMax(result.getInt("nivelMax"));
            classe.setSistema(sistemaDB.buscaPorCodigo(result.getInt("sistemaId")));
            classes.add(classe);
        }
        close();
        return classes;
    }
}
