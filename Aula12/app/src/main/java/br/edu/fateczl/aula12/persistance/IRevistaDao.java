package br.edu.fateczl.aula12.persistance;

import java.sql.SQLException;


public interface IRevistaDao {
    public RevistaDao open() throws SQLException;
    public void close();
}
