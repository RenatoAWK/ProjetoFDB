package com.bsi.fbd.minisiga.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Curso implements Parcelable {
    private int codigo;
    private String nome;
    private String sigla;
    private int ramal;
    private int duracao;
    private String sigla_faculdade;
    private int codigo_bloco;

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

    public int getRamal() {
        return ramal;
    }

    public void setRamal(int ramal) {
        this.ramal = ramal;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getCodigo_bloco() {
        return codigo_bloco;
    }

    public void setCodigo_bloco(int codigo_bloco) {
        this.codigo_bloco = codigo_bloco;
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
        dest.writeString(this.nome);
        dest.writeString(this.sigla);
        dest.writeInt(this.ramal);
        dest.writeInt(this.duracao);
        dest.writeString(this.sigla_faculdade);
        dest.writeInt(this.codigo_bloco);
    }

    public Curso() {
    }

    protected Curso(Parcel in) {
        this.codigo = in.readInt();
        this.nome = in.readString();
        this.sigla = in.readString();
        this.ramal = in.readInt();
        this.duracao = in.readInt();
        this.sigla_faculdade = in.readString();
        this.codigo_bloco = in.readInt();
    }

    public static final Creator<Curso> CREATOR = new Creator<Curso>() {
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
