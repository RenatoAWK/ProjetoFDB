package com.bsi.fbd.minisiga.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Faculdade implements Parcelable {
    private String sigla;
    private String nome;
    private String cidade;
    private String endereco;
    private String senha;
    private String email;
    private String data;

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sigla);
        dest.writeString(this.nome);
        dest.writeString(this.cidade);
        dest.writeString(this.endereco);
        dest.writeString(this.senha);
        dest.writeString(this.email);
        dest.writeString(this.data);
    }

    public Faculdade() {
    }

    protected Faculdade(Parcel in) {
        this.sigla = in.readString();
        this.nome = in.readString();
        this.cidade = in.readString();
        this.endereco = in.readString();
        this.senha = in.readString();
        this.email = in.readString();
        this.data = in.readString();
    }

    public static final Parcelable.Creator<Faculdade> CREATOR = new Parcelable.Creator<Faculdade>() {
        @Override
        public Faculdade createFromParcel(Parcel source) {
            return new Faculdade(source);
        }

        @Override
        public Faculdade[] newArray(int size) {
            return new Faculdade[size];
        }
    };
}
