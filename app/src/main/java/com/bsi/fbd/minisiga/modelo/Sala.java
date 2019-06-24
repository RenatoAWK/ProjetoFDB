package com.bsi.fbd.minisiga.modelo;

public class Sala {
    private int numero;
    private String area;
    private String tipo;
    private String siglaFaculdade;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
}
