package com.trabalhojava.sistemarpg.model;

public class PersonagemSistema {
    private Personagem personagem;
    private Sistema sistema;
    private Raca raca;
    private Classe classe;
    private int hp;
    private int nivel;
    private int forca;
    private int destreza;
    private int constituicao;
    private int inteligencia;
    private int sabedoria;
    private int carisma;

    public PersonagemSistema() {}

    public PersonagemSistema(Personagem personagem, Sistema sistema, Raca raca, Classe classe) {
        this.personagem = personagem;
        this.sistema = sistema;
        this.raca = raca;
        this.classe = classe;
        this.nivel = validarNivel(personagem.getNivel(), classe.getNivelMax());
        this.forca = validarAtributo(personagem.getForca(), raca.getForca());
        this.destreza = validarAtributo(personagem.getDestreza(), raca.getDestreza());
        this.constituicao = validarAtributo(personagem.getConstituicao(), raca.getConstituicao());
        this.inteligencia = validarAtributo(personagem.getInteligencia(), raca.getInteligencia());
        this.sabedoria = validarAtributo(personagem.getSabedoria(), raca.getSabedoria());
        this.carisma = validarAtributo(personagem.getCarisma(), raca.getCarisma());
        this.hp = validarHp(classe.getHpInicial(), classe.getHpNivel(), personagem.getNivel(), this.constituicao);
    }

    private int validarNivel(int nDesejado, int nMax) {
        if(nDesejado > nMax)
        {
            nDesejado = nMax;
        }
        return nDesejado;
    }

    private int validarAtributo(int aPersonagem, int aRaca) {
        return aPersonagem + aRaca;
    }

    private int validarHp(int hpClasseInicial, int hpClasse, int nivel, int constituicao) {
        return (hpClasseInicial + constituicao) + ((hpClasse + constituicao) * (nivel - 1));
    }

    public Sistema getSistema() {
        return sistema;
    }

    public Classe getClasse() {
        return classe;
    }

    public Raca getRaca() {
        return raca;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public int getHp() {
        return hp;
    }

    public int getNivel() {
        return nivel;
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

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
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

    public String toString() {
        return "Personagem_Sistema {personagemId=" + personagem.getPersonagemId() +
                ", sistemaId = " + sistema.getSistemaId() +
                ", racaid = " + raca.getRacaId() +
                ", classeId = " + classe.getClasseId() +
                ", hp = " + hp +
                ", nivel = " + nivel +
                ", forca = " + forca +
                ", destreza = " + destreza +
                ", constituicao = " + constituicao +
                ", inteligencia = " + inteligencia +
                ", sabedoria = " + sabedoria +
                ", carisma = " + carisma + "}";
    }
}
