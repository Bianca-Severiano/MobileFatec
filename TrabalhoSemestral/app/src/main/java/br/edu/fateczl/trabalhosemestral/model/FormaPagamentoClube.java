package br.edu.fateczl.trabalhosemestral.model;

public abstract class FormaPagamento {

    private String nomeTitular;
    private String tipo;


    public FormaPagamento() {
     super();
    }


    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "FormaPagamento =>" +
                " Nome do Titular: " + nomeTitular +
                " Tipo: " + tipo;
    }
}
