package br.edu.fateczl.trabalhosemestral.persistence;

import java.sql.SQLException;

public interface IPagamentoDebito {
    public PagamentoDebitoDao open() throws SQLException;
    public void close() throws SQLException;
}
