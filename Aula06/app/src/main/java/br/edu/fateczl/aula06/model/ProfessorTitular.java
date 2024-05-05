package br.edu.fateczl.aula06.model;

public class ProfessorTitular extends Professor{

    private int anosInstituicao;
    private double salario;

    @Override
    public double calculoSalario() {
        double novoSalario;
        novoSalario = salario + (((salario * 5)/100)*(anosInstituicao/5));
        return novoSalario;
    }

    public ProfessorTitular (){
        super();
    }

    public int getAnosInstituicao() {
        return anosInstituicao;
    }

    public void setAnosInstituicao(int anosInstituicao) {
        this.anosInstituicao = anosInstituicao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
