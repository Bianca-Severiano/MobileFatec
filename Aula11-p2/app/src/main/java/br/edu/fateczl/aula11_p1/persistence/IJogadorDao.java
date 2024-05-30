package br.edu.fateczl.aula11_p1.persistence;

import java.sql.SQLException;

import br.edu.fateczl.aula11_p1.model.Jogador;

public interface IJogadorDao {
    public JogadorDao open() throws SQLException;
    public void close() throws SQLException;
}
