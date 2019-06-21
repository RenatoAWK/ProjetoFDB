package com.bsi.fbd.minisiga.modelo;

public class Sala {
    private int numero;
    private String area;
    private int tipo;
    private String siglaFaculdade;
    private String nomeFaculdade;
    private int codigoBloco;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getSiglaFaculdade() {
        return siglaFaculdade;
    }

    public void setSiglaFaculdade(String siglaFaculdade) {
        this.siglaFaculdade = siglaFaculdade;
    }

    public String getNomeFaculdade() {
        return nomeFaculdade;
    }

    public void setNomeFaculdade(String nomeFaculdade) {
        this.nomeFaculdade = nomeFaculdade;
    }

    public int getCodigoBloco() {
        return codigoBloco;
    }

    public void setCodigoBloco(int codigoBloco) {
        this.codigoBloco = codigoBloco;
    }
}
