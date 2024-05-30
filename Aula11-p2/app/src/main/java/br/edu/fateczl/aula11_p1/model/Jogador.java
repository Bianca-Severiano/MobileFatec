package br.edu.fateczl.aula11_p1.model;

public class Jogador {

    private int id;
    private String nome;
    private String dataNasc;
    private Double altura;
    private Double peso;
    private Time time;

    public Jogador() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Time: " + time.getNome() + ", Id: " + id + ", Nascimento: " +
        dataNasc + ", Altura: " + altura + "m, Peso: " + peso + "kg";
    }
}
