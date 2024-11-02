package com.trabalhojava.sistemarpg.model;

public class Raca {
    private int racaId;
    private String nomeRaca;
    private String descricao;
    private int forca;
    private int destreza;
    private int constituicao;
    private int inteligencia;
    private int sabedoria;
    private int carisma;
    private Sistema sistema;

    public Raca() {
    }

    public Raca(int racaId, String nomeRaca, String descricao, int forca, int destreza, int constituicao,
                int inteligencia, int sabedoria, int carisma, Sistema sistema) {
        this.racaId = racaId;
        this.nomeRaca = nomeRaca;
        this.descricao = descricao;
        this.forca = forca;
        this.destreza = destreza;
        this.constituicao = constituicao;
        this.inteligencia = inteligencia;
        this.sabedoria = sabedoria;
        this.carisma = carisma;
        this.sistema = sistema;
    }

    public int getRacaId() {
        return racaId;
    }

    public String getNomeRaca() {
        return nomeRaca;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getForca() {
        return forca;
    }

    public int getDestreza() {
        return destreza;
    }

    public int getConstituicao() {
        return constituicao;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public int getSabedoria() {
        return sabedoria;
    }

    public int getCarisma() {
        return carisma;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setRacaId(int racaId) {
        this.racaId = racaId;
    }

    public void setNomeRaca(String nomeRaca) {
        this.nomeRaca = nomeRaca;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public void setConstituicao(int constituicao) {
        this.constituicao = constituicao;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public void setSabedoria(int sabedoria) {
        this.sabedoria = sabedoria;
    }

    public void setCarisma(int carisma) {
        this.carisma = carisma;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    @Override
    public String toString() {
        return "Raça {racaid = " + racaId +
                ", nomeRaca = " + nomeRaca +
                ", descricao = " + descricao +
                ", forca = " + forca +
                ", destreza = " + destreza +
                ", constituicao = " + constituicao +
                ", inteligencia = " + inteligencia +
                ", sabedoria = " + sabedoria +
                ", carisma = " + carisma +
                ", sistemaId = " + sistema.getSistemaId() + "}";
    }
}

