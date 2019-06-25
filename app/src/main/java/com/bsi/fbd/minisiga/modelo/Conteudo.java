package com.bsi.fbd.minisiga.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Conteudo implements Parcelable {
    private String item;
    private int cargaHoraria;
    private String sigla_faculdade;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
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
        dest.writeString(this.item);
        dest.writeInt(this.cargaHoraria);
        dest.writeString(this.sigla_faculdade);
    }

    public Conteudo() {
    }

    protected Conteudo(Parcel in) {
        this.item = in.readString();
        this.cargaHoraria = in.readInt();
        this.sigla_faculdade = in.readString();
    }

    public static final Creator<Conteudo> CREATOR = new Creator<Conteudo>() {
        @Override
        public Conteudo createFromParcel(Parcel source) {
            return new Conteudo(source);
        }

        @Override
        public Conteudo[] newArray(int size) {
            return new Conteudo[size];
        }
    };
}
