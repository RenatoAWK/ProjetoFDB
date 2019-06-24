package com.bsi.fbd.minisiga.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Aluno implements Parcelable {
    private String cpf;
    private String nome;
    private String endereco;
    private String email;
    private String senha;
    private String siglaFaculdade;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        dest.writeString(this.cpf);
        dest.writeString(this.nome);
        dest.writeString(this.endereco);
        dest.writeString(this.email);
        dest.writeString(this.senha);
        dest.writeString(this.siglaFaculdade);
    }

    public Aluno() {
    }

    protected Aluno(Parcel in) {
        this.cpf = in.readString();
        this.nome = in.readString();
        this.endereco = in.readString();
        this.email = in.readString();
        this.senha = in.readString();
        this.siglaFaculdade = in.readString();
    }

    public static final Parcelable.Creator<Aluno> CREATOR = new Parcelable.Creator<Aluno>() {
        @Override
        public Aluno createFromParcel(Parcel source) {
            return new Aluno(source);
        }

        @Override
        public Aluno[] newArray(int size) {
            return new Aluno[size];
        }
    };
}
