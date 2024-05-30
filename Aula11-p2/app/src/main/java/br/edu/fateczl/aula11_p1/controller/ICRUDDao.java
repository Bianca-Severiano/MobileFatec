package br.edu.fateczl.aula11_p1.controller;

import java.sql.SQLException;
import java.util.List;

public interface ICRUDDao <T> {

    public void inserir (T t) throws SQLException, ClassNotFoundException;
    public void atualizar (T t) throws SQLException, ClassNotFoundException;
    public void deletar(T t) throws SQLException, ClassNotFoundException;
    public T buscar(T t) throws SQLException, ClassNotFoundException;
    public List<T> listar() throws SQLException, ClassNotFoundException;
}
