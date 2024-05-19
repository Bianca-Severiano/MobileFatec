package br.edu.fateczl.aula08.model;

public class IngressoVIP extends  Ingresso{

    private String funcaoDesempenhada;

    public IngressoVIP(String funcaoDesempenhada) {
        this.funcaoDesempenhada = funcaoDesempenhada;
    }

    public String getFuncaoDesempenhada() {
        return funcaoDesempenhada;
    }

    public void setFuncaoDesempenhada(String funcaoDesempenhada) {
        this.funcaoDesempenhada = funcaoDesempenhada;
    }

    @Override
    public float valorFinal(float taxa) {
        float acressimo = getValor() * 0.18f;
        return super.valorFinal(taxa) + acressimo;
    }

    @Override
    public String toString() {
        return "Ingresso:" + getIdentificador() +  '\n' +
                "Valor: R$" + getValor() +  '\n' +
                "Função: " + funcaoDesempenhada;
    }
}
