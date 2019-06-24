package com.bsi.fbd.minisiga.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Conteudo implements Parcelable {
    private String item;
    private int cargaHoraria;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.item);
        dest.writeInt(this.cargaHoraria);
    }

    public Conteudo() {
    }

    protected Conteudo(Parcel in) {
        this.item = in.readString();
        this.cargaHoraria = in.readInt();
    }

    public static final Parcelable.Creator<Conteudo> CREATOR = new Parcelable.Creator<Conteudo>() {
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
