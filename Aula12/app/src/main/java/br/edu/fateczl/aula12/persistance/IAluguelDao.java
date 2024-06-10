package br.edu.fateczl.aula12.persistance;

import java.sql.SQLException;

public interface IAluguelDao {
    public AluguelDao open() throws SQLException;
    public void close();
}
