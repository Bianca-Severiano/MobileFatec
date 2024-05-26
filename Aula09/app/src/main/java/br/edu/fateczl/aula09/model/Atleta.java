package br.edu.fateczl.aula09.model;

import androidx.annotation.NonNull;

import java.util.Date;

public abstract class Atleta {

    private String nome;
    private Date data_nasc;
    private String bairro;

    public Atleta() {
        super();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
