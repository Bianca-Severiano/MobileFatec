package br.edu.fateczl.aula11_p1.persistence;

import java.sql.SQLException;
import java.util.List;

public interface ICRUDDao <T>{

    public void inserir (T t) throws SQLException, ClassNotFoundException;
    public void atualizar(T t) throws SQLException, ClassNotFoundException;
    public void excluir(T t) throws SQLException, ClassNotFoundException;
    public T buscar (T t)  throws SQLException, ClassNotFoundException;
    public List<T> listarTodos() throws SQLException, ClassNotFoundException;
}
