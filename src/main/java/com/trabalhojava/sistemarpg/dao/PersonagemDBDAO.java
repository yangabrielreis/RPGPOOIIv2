package com.trabalhojava.sistemarpg.dao;

import com.trabalhojava.sistemarpg.model.Personagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonagemDBDAO implements PersonagemDAO, IConst {
    private String sql;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    public PersonagemDBDAO() {
    }

    private void open() throws SQLException {
        connection = Conexao.getConexao("jdbc:postgresql://localhost:5432/sistemarpg", "postgres", "postgres");
    }

    private void close() throws SQLException {
        connection.close();
    }

    public void insere(Personagem personagem) throws SQLException {
        open();
        sql = "INSERT INTO personagem(personagemId,nome,descricao,urlImg,nivel,forca,destreza,constituicao,inteligencia,sabedoria,carisma) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, personagem.getPersonagemId());
        statement.setString(2, personagem.getNome());
        statement.setString(3, personagem.getDescricao());
        statement.setString(4, personagem.getUrlImg());
        statement.setInt(5, personagem.getNivel());
        statement.setInt(6, personagem.getForca());
        statement.setInt(7, personagem.getDestreza());
        statement.setInt(8, personagem.getConstituicao());
        statement.setInt(9, personagem.getInteligencia());
        statement.setInt(10, personagem.getSabedoria());
        statement.setInt(11, personagem.getCarisma());
        statement.executeUpdate();
        close();
    }

    public void atualizar(Personagem personagem) throws SQLException {
        open();
        sql = "UPDATE personagem SET nome=?, descricao=?, urlImg=?, nivel=?, forca=?, destreza=?, constituicao=?, inteligencia=?, sabedoria=?, carisma=? WHERE personagemId=?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, personagem.getNome());
        statement.setString(2, personagem.getDescricao());
        statement.setString(3, personagem.getUrlImg());
        statement.setInt(4, personagem.getNivel());
        statement.setInt(5, personagem.getForca());
        statement.setInt(6, personagem.getDestreza());
        statement.setInt(7, personagem.getConstituicao());
        statement.setInt(8, personagem.getInteligencia());
        statement.setInt(9, personagem.getSabedoria());
        statement.setInt(10, personagem.getCarisma());
        statement.setInt(11, personagem.getPersonagemId());
        statement.executeUpdate();
        close();

    }

    public void remover(Personagem personagem) throws SQLException {
        open();
        sql = "DELETE FROM personagem WHERE personagemId=?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1,personagem.getPersonagemId());
        statement.executeUpdate();
        close();
    }

    public Personagem buscaPorCodigo(int personagemId) throws SQLException {
        open();
        sql = "SELECT * FROM personagem WHERE personagemId=?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, personagemId);
        result = statement.executeQuery();
        if (result.next()) {
            Personagem personagem = new Personagem();
            personagem.setPersonagemId(result.getInt("personagemId"));
            personagem.setNome(result.getString("nome"));
            personagem.setDescricao(result.getString("descricao"));
            personagem.setUrlImg(result.getString("urlImg"));
            personagem.setNivel(result.getInt("nivel"));
            personagem.setForca(result.getInt("forca"));
            personagem.setDestreza(result.getInt("destreza"));
            personagem.setConstituicao(result.getInt("constituicao"));
            personagem.setInteligencia(result.getInt("inteligencia"));
            personagem.setSabedoria(result.getInt("sabedoria"));
            personagem.setCarisma(result.getInt("carisma"));
            close();
            return personagem;
        }
        else {
            close();
            return null;
        }
    }

    public List<Personagem> listar() throws SQLException {
        open();
        sql = "SELECT * FROM Personagem";
        statement = connection.prepareStatement(sql);
        result = statement.executeQuery();
        ArrayList<Personagem> personagens = new ArrayList<>();

        while (result.next()) {
            Personagem personagem = new Personagem();
            personagem.setPersonagemId(result.getInt("personagemId"));
            personagem.setNome(result.getString("nome"));
            personagem.setDescricao(result.getString("descricao"));
            personagem.setUrlImg(result.getString("urlImg"));
            personagem.setNivel(result.getInt("nivel"));
            personagem.setForca(result.getInt("forca"));
            personagem.setDestreza(result.getInt("destreza"));
            personagem.setConstituicao(result.getInt("constituicao"));
            personagem.setInteligencia(result.getInt("inteligencia"));
            personagem.setSabedoria(result.getInt("sabedoria"));
            personagem.setCarisma(result.getInt("carisma"));
            personagens.add(personagem);
        }
        close();
        return personagens;
    }
}
