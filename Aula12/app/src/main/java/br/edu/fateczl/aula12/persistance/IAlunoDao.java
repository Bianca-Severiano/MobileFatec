package br.edu.fateczl.aula12.persistance;

import java.sql.SQLException;

import br.edu.fateczl.aula12.controller.ICRUDDao;
import br.edu.fateczl.aula12.model.Aluno;

public interface IAlunoDao {
    public AlunoDao open() throws SQLException;
    public void close();
}
