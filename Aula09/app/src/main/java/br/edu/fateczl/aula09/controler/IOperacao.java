package br.edu.fateczl.aula09.controler;

public interface IOperacao<T> {
    public void cadastrar (T t);

    public String listarAtleta (T t);
}
