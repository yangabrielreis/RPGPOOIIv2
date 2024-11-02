package com.trabalhojava.sistemarpg.main;

import java.sql.SQLException;

import com.trabalhojava.sistemarpg.dao.PersonagemDBDAO;
import com.trabalhojava.sistemarpg.dao.SistemaDBDAO;
import com.trabalhojava.sistemarpg.dao.RacaDBDAO;
import com.trabalhojava.sistemarpg.dao.ClasseDBDAO;
import com.trabalhojava.sistemarpg.dao.PersonagemSistemaDBDAO;
import com.trabalhojava.sistemarpg.model.Personagem;
import com.trabalhojava.sistemarpg.model.Sistema;
import com.trabalhojava.sistemarpg.model.Raca;
import com.trabalhojava.sistemarpg.model.Classe;
import com.trabalhojava.sistemarpg.model.PersonagemSistema;

public class Main {
    public static void main(String[] args) throws SQLException{
        Personagem personagem = new Personagem(1,"Singus","The GOAT","Imagem Foda",17,-1,3,4,7,2,-2);
        Personagem personagem1 = new Personagem(2,"Degnis","The B","Imagem",12,-1,3,4,7,2,-2);
        PersonagemDBDAO personagemDAO = new PersonagemDBDAO();
        System.out.println("INSERE PERSONAGENS");
        personagemDAO.insere(personagem);
        personagemDAO.insere(personagem1);
        Personagem personagem2 = new Personagem(1,"Singed","GOAT","Imagem 2",18,-1,3,4,7,2,-2);
        System.out.println("ATUALIZA PERSONAGEM");
        personagemDAO.atualizar(personagem2);
        System.out.println("LISTA PERSONAGEM");
        System.out.println(personagemDAO.listar());
        System.out.println("REMOVE PERSONAGEM");
        personagemDAO.remover(personagem1);

        Sistema sistema1 = new Sistema(1,"Torment");
        Sistema sistema2 = new Sistema(2,"Call of Cthulhu");
        SistemaDBDAO sistemaDAO = new SistemaDBDAO();
        System.out.println("INSERE SISTEMAS");
        sistemaDAO.insere(sistema1);
        sistemaDAO.insere(sistema2);
        Sistema sistema3 = new Sistema(1,"Tormenta");
        System.out.println("ATUALIZA SISTEMA");
        sistemaDAO.atualizar(sistema3);
        System.out.println("LISTA SISTEMA");
        System.out.println(sistemaDAO.listar());
        System.out.println("REMOVE SISTEMA");
        sistemaDAO.remover(sistema2);

        Raca raca1 = new Raca(1,"Gobli", "Goblin moments", 0, 0, 1, 2, 0, -1, sistema1);
        Raca raca2 = new Raca(2,"Humano", "human buxa", 1, 1, 1, 1, 1, 1, sistema1);
        RacaDBDAO racaDAO = new RacaDBDAO();
        System.out.println("INSERE RAÇAS");
        racaDAO.insere(raca1);
        racaDAO.insere(raca2);
        Raca raca3 = new Raca(1,"Goblin", "Goblin moments", 0, 0, 1, 2, 0, -1, sistema1);
        System.out.println("ATUALIZA RAÇAS");
        racaDAO.atualizar(raca3);
        System.out.println("LISTA RAÇAS");
        System.out.println(racaDAO.listar());
        System.out.println("REMOVE SISTEMA");
        racaDAO.remover(raca2);

        Classe classe1 = new Classe(1,"Invento","Inventor Moments", 12,3,20,sistema1);
        Classe classe2 = new Classe(2,"Gurerreiro", "Guerreiro Buxa", 20, 5, 20, sistema1);
        ClasseDBDAO classeDAO = new ClasseDBDAO();
        System.out.println("INSERE CLASSES");
        classeDAO.insere(classe1);
        classeDAO.insere(classe2);
        Classe classe3 = new Classe(1,"Inventor","Inventor Moments", 12,3,20,sistema1);
        System.out.println("ATUALIZA CLASSES");
        classeDAO.atualizar(classe3);
        System.out.println("LISTA CLASSES");
        System.out.println(classeDAO.listar());
        System.out.println("REMOVE CLASSES");
        classeDAO.remover(classe2);

        Personagem personagem3 = new Personagem(5,"Shimira", "Monge The Goat", "Imagem 42", 20, 4,8,6,2,6,-1);
        personagemDAO.insere(personagem3);
        Sistema sistema = new Sistema(5,"Tormenta 20");
        sistemaDAO.insere(sistema);
        Raca raca = new Raca(5,"Mashin","Robo de Tamu-Ra",1,1,1,1,1,1,sistema);
        racaDAO.insere(raca);
        Classe classe = new Classe(5,"Monge","Lutador Corpo a Corpo que usa da paz interior",20,5,20,sistema);
        classeDAO.insere(classe);

        PersonagemSistema personagemSistema1 = new PersonagemSistema(personagem2,sistema3,raca3,classe3);
        PersonagemSistema personagemSistema2 = new PersonagemSistema(personagem3,sistema,raca,classe);
        PersonagemSistemaDBDAO personagemSistemaDAO = new PersonagemSistemaDBDAO();
        System.out.println("INSERE PERSONAGEM_SISTEMA");
        personagemSistemaDAO.insere(personagemSistema1);
        personagemSistemaDAO.insere(personagemSistema2);
        System.out.println("ATUALIZA PERSONAGEM_SISTEMA");
        PersonagemSistema personagemSistema3 = new PersonagemSistema(personagem2,sistema3,raca3,classe);
        personagemSistemaDAO.atualizar(personagemSistema1, personagemSistema3);
        System.out.println("LISTA PERSONAGEM_SISTEMA");
        System.out.println(personagemSistemaDAO.listar());
        System.out.println("REMOVE PERSONAGEM_SISTEMA");
        personagemSistemaDAO.remover(personagemSistema3);
    }
}
