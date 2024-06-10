package br.edu.fateczl.aula12.persistance;

import java.sql.SQLException;

public interface ILivroDao {
    public LivroDao open() throws SQLException;
    public void close();
}
