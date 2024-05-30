package br.edu.fateczl.aula11_p1.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.aula11_p1.model.Time;
import br.edu.fateczl.aula11_p1.persistence.TimeDao;

public class TimeController implements ICRUDDao<Time>{

    private final TimeDao tDao;

    public  TimeController(TimeDao tDao){
        this.tDao = tDao;
    }

    @Override
    public void inserir(Time time) throws SQLException, ClassNotFoundException {
        if (tDao == null){
            tDao.open();
        }
        tDao.inserir(time);
        tDao.close();
    }

    @Override
    public void atualizar(Time time) throws SQLException, ClassNotFoundException {
        if (tDao == null){
            tDao.open();
        }
        tDao.atualizar(time);
        tDao.close();
    }

    @Override
    public void deletar(Time time) throws SQLException, ClassNotFoundException {
        if (tDao == null){
            tDao.open();
        }
        tDao.excluir(time);
        tDao.close();
    }

    @Override
    public Time buscar(Time time) throws SQLException, ClassNotFoundException {
        if (tDao == null){
            tDao.open();
        }
        return tDao.buscar(time);

    }

    @Override
    public List<Time> listar() throws SQLException, ClassNotFoundException {
        if (tDao == null){
            tDao.open();
        }
        return tDao.listarTodos();

    }
}
