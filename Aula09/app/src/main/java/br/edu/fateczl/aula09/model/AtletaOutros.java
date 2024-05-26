package br.edu.fateczl.aula09.model;

import androidx.annotation.NonNull;

public class AtletaOutros extends Atleta{

    private String academia;
    private float recordeSegundos;

    public AtletaOutros() {
        super();
    }

    public String getAcademia() {
        return academia;
    }

    public void setAcademia(String academia) {
        this.academia = academia;
    }

    public float getRecordeSegundos() {
        return recordeSegundos;
    }

    public void setRecordeSegundos(float recordeSegundos) {
        this.recordeSegundos = recordeSegundos;
    }

    @NonNull
    @Override
    public String toString() {
        return "Nome: " + getNome() + " Academia: " + academia + " Data Nascimento" + getData_nasc() + " Recorde: " + recordeSegundos + "s";
    }
}
