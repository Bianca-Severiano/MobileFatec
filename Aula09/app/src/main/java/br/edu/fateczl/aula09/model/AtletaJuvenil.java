package br.edu.fateczl.aula09.model;

import androidx.annotation.NonNull;

public class AtletaJuvenil extends Atleta{

    private int anosPratica;

    public AtletaJuvenil() {
        super();
    }

    public int getAnosPratica() {
        return anosPratica;
    }

    public void setAnosPratica(int anosPratica) {
        this.anosPratica = anosPratica;
    }

    @NonNull
    @Override
    public String toString() {

        return "Nome: " + getNome() + " Anos de prática: " + anosPratica + " Data Nascimento" + getData_nasc();
    }
}
