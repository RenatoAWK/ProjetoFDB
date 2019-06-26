package com.bsi.fbd.minisiga.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Turma implements Parcelable {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.codigo);
        dest.writeString(this.ano);
        dest.writeString(this.siglaFaculdade);
        dest.writeInt(this.codigoBloco);
        dest.writeInt(this.numeroSala);
        dest.writeInt(this.codigoCurso);
    }

    public Turma() {
    }

    protected Turma(Parcel in) {
        this.codigo = in.readInt();
        this.ano = in.readString();
        this.siglaFaculdade = in.readString();
        this.codigoBloco = in.readInt();
        this.numeroSala = in.readInt();
        this.codigoCurso = in.readInt();
    }

    public static final Creator<Turma> CREATOR = new Creator<Turma>() {
        @Override
        public Turma createFromParcel(Parcel source) {
            return new Turma(source);
        }

        @Override
        public Turma[] newArray(int size) {
            return new Turma[size];
        }
    };
}
