package com.bsi.fbd.minisiga.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class RecursoDidatico implements Parcelable {
    private int codigo;
    private String nome;
    private String descricao;
    private String data;
    private String horario;
    private String cpfProfessor;
    private String sigla_faculdade;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getCpfProfessor() {
        return cpfProfessor;
    }

    public void setCpfProfessor(String cpfProfessor) {
        this.cpfProfessor = cpfProfessor;
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
        dest.writeString(this.descricao);
        dest.writeString(this.data);
        dest.writeString(this.horario);
        dest.writeString(this.cpfProfessor);
        dest.writeString(this.sigla_faculdade);
    }

    public RecursoDidatico() {
    }

    protected RecursoDidatico(Parcel in) {
        this.codigo = in.readInt();
        this.nome = in.readString();
        this.descricao = in.readString();
        this.data = in.readString();
        this.horario = in.readString();
        this.cpfProfessor = in.readString();
        this.sigla_faculdade = in.readString();
    }

    public static final Creator<RecursoDidatico> CREATOR = new Creator<RecursoDidatico>() {
        @Override
        public RecursoDidatico createFromParcel(Parcel source) {
            return new RecursoDidatico(source);
        }

        @Override
        public RecursoDidatico[] newArray(int size) {
            return new RecursoDidatico[size];
        }
    };
}
