package com.bsi.fbd.minisiga.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Sala implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.numero);
        dest.writeString(this.area);
        dest.writeString(this.tipo);
        dest.writeString(this.siglaFaculdade);
        dest.writeInt(this.codigoBloco);
    }

    public Sala() {
    }

    protected Sala(Parcel in) {
        this.numero = in.readInt();
        this.area = in.readString();
        this.tipo = in.readString();
        this.siglaFaculdade = in.readString();
        this.codigoBloco = in.readInt();
    }

    public static final Parcelable.Creator<Sala> CREATOR = new Parcelable.Creator<Sala>() {
        @Override
        public Sala createFromParcel(Parcel source) {
            return new Sala(source);
        }

        @Override
        public Sala[] newArray(int size) {
            return new Sala[size];
        }
    };
}
