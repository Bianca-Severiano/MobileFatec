package br.edu.fateczl.aula11_p1.persistence;

import java.sql.SQLException;

public interface ITimeDao {

    public TimeDao open() throws SQLException;
    public void close() throws SQLException;
}
