package br.edu.fateczl.aula11_p1.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.aula11_p1.model.Jogador;
import br.edu.fateczl.aula11_p1.persistence.JogadorDao;

public class JogadorController implements ICRUDDao <Jogador> {

    private final JogadorDao jDao;

    public JogadorController(JogadorDao jdao){
        jDao = jdao;
    }

    @Override
    public void inserir(Jogador jogador) throws SQLException, ClassNotFoundException {
        if (jDao == null){
            jDao.open();
        }
        jDao.inserir(jogador);
        jDao.close();
    }

    @Override
    public void atualizar(Jogador jogador) throws SQLException, ClassNotFoundException {
        if (jDao == null){
            jDao.open();
        }
        jDao.atualizar(jogador);
        jDao.close();
    }

    @Override
    public void deletar(Jogador jogador) throws SQLException, ClassNotFoundException {
        if (jDao == null){
            jDao.open();
        }
        jDao.excluir(jogador);
        jDao.close();
    }

    @Override
    public Jogador buscar(Jogador jogador) throws SQLException, ClassNotFoundException {
        if (jDao == null){
            jDao.open();
        }

        return jDao.buscar(jogador);
    }

    @Override
    public List<Jogador> listar() throws SQLException, ClassNotFoundException {
        if (jDao == null){
            jDao.open();
        }

        return jDao.listarTodos();
    }
}
