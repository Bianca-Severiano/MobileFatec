package br.edu.fateczl.aula09.model;

import androidx.annotation.NonNull;

public class AtletaSenior extends Atleta{

    private String problemaCardiaco;

    public AtletaSenior() {
        super();
    }

    public String getProblemaCardiaco() {
        return problemaCardiaco;
    }

    public void setProblemaCardiaco(String problemaCardiaco) {
        this.problemaCardiaco = problemaCardiaco;
    }

    @NonNull
    @Override
    public String toString() {
        return "Nome:" + getNome() + " Problema cardiáco: " + problemaCardiaco + " Data Nascimento: " + getData_nasc();
    }
}
