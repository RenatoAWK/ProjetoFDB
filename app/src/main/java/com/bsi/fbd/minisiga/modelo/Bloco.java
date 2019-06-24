package com.bsi.fbd.minisiga.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Bloco implements Parcelable {
    private int codigo;
    private String area;
    private String tipo;
    private String siglaFaculdade;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.codigo);
        dest.writeString(this.area);
        dest.writeString(this.tipo);
        dest.writeString(this.siglaFaculdade);
    }

    public Bloco() {
    }

    protected Bloco(Parcel in) {
        this.codigo = in.readInt();
        this.area = in.readString();
        this.tipo = in.readString();
        this.siglaFaculdade = in.readString();
    }

    public static final Parcelable.Creator<Bloco> CREATOR = new Parcelable.Creator<Bloco>() {
        @Override
        public Bloco createFromParcel(Parcel source) {
            return new Bloco(source);
        }

        @Override
        public Bloco[] newArray(int size) {
            return new Bloco[size];
        }
    };
}
