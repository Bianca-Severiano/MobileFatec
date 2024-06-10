package br.edu.fateczl.aula12.model;

public class Revista extends Exemplar{
    private String ISSN;

    public String getISSN() {
        return ISSN;
    }

    public void setISSN(String iSSN) {
        ISSN = iSSN;
    }

    public Revista() {
        super();
    }

    @Override
    public String toString() {
        return "#"+getCodigo()+" "+getNome();
    }
}
