package com.bsi.fbd.minisiga.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Disciplina implements Parcelable {
    private int codigo;
    private int cargaHoraria;
    private String nome;
    private String sigla_faculdade;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla_faculdade() {
        return sigla_faculdade;
    }

    public void setSigla_faculdade(String sigla_faculdade) {
        this.sigla_faculdade = sigla_faculdade;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.codigo);
        dest.writeInt(this.cargaHoraria);
        dest.writeString(this.nome);
        dest.writeString(this.sigla_faculdade);
    }

    public Disciplina() {
    }

    protected Disciplina(Parcel in) {
        this.codigo = in.readInt();
        this.cargaHoraria = in.readInt();
        this.nome = in.readString();
        this.sigla_faculdade = in.readString();
    }

    public static final Creator<Disciplina> CREATOR = new Creator<Disciplina>() {
        @Override
        public Disciplina createFromParcel(Parcel source) {
            return new Disciplina(source);
        }

        @Override
        public Disciplina[] newArray(int size) {
            return new Disciplina[size];
        }
    };
}
