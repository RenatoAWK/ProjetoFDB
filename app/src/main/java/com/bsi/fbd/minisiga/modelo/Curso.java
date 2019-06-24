package com.bsi.fbd.minisiga.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Curso implements Parcelable {
    private int codigo;
    private String nome;
    private String sigla;
    private String ramal;
    private int duracao;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.codigo);
        dest.writeString(this.nome);
        dest.writeString(this.sigla);
        dest.writeString(this.ramal);
        dest.writeInt(this.duracao);
    }

    public Curso() {
    }

    protected Curso(Parcel in) {
        this.codigo = in.readInt();
        this.nome = in.readString();
        this.sigla = in.readString();
        this.ramal = in.readString();
        this.duracao = in.readInt();
    }

    public static final Parcelable.Creator<Curso> CREATOR = new Parcelable.Creator<Curso>() {
        @Override
        public Curso createFromParcel(Parcel source) {
            return new Curso(source);
        }

        @Override
        public Curso[] newArray(int size) {
            return new Curso[size];
        }
    };
}
