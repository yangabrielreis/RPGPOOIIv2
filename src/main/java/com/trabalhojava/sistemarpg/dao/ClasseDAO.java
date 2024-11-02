package com.trabalhojava.sistemarpg.dao;

import java.sql.SQLException;
import java.util.List;
import com.trabalhojava.sistemarpg.model.Classe;

public interface ClasseDAO  {
    void insere(Classe var1) throws SQLException;
    void atualizar(Classe var1) throws SQLException;
    void remover(Classe var1) throws SQLException;
    Classe buscaPorCodigo(int classeId) throws SQLException;
    List<Classe> listar() throws SQLException;
}
