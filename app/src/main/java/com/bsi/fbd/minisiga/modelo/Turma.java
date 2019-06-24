package com.bsi.fbd.minisiga.modelo;

public class Turma {
    private int codigo;
    private String ano;
    private String siglaFaculdade;
    private int codigoBloco;
    private int numeroSala;
    private int codigoCurso;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getSiglaFaculdade() {
        return siglaFaculdade;
    }

    public void setSiglaFaculdade(String siglaFaculdade) {
        this.siglaFaculdade = siglaFaculdade;
    }

    public int getCodigoBloco() {
        return codigoBloco;
    }

    public void setCodigoBloco(int codigoBloco) {
        this.codigoBloco = codigoBloco;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }
}
