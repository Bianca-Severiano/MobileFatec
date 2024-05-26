package br.edu.fateczl.aula11_p1.controller;

import java.sql.SQLException;
import java.util.List;

public interface ICRUDDao <T> {

    public void insert(T t) throws SQLException;
    public int update(T t) throws SQLException;
    public void delete(T t) throws SQLException;
    public T finOne(T t) throws SQLException;
    public List<T> findAll() throws SQLException;
}
