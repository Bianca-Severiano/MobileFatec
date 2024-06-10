package br.edu.fateczl.trabalhosemestral.model;

public class Debito extends FormaPagamentoClube {

    private String chave;

    public Debito() {
        super();
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Chave: " + chave;
    }
}
