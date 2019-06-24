package com.bsi.fbd.minisiga.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Disciplina implements Parcelable {
    private int codigo;
    private int cargaHoraria;
    private String nome;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.codigo);
        dest.writeInt(this.cargaHoraria);
        dest.writeString(this.nome);
    }

    public Disciplina() {
    }

    protected Disciplina(Parcel in) {
        this.codigo = in.readInt();
        this.cargaHoraria = in.readInt();
        this.nome = in.readString();
    }

    public static final Parcelable.Creator<Disciplina> CREATOR = new Parcelable.Creator<Disciplina>() {
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
