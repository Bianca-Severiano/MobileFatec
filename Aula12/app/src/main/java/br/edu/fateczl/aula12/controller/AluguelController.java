package br.edu.fateczl.aula12.controller;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.aula12.model.Aluguel;
import br.edu.fateczl.aula12.persistance.AluguelDao;

public class AluguelController implements IController<Aluguel> {

    private AluguelDao alDao;

    public AluguelController(AluguelDao alDao) {
        this.alDao = alDao;
    }

    @Override
    public void insert(Aluguel aluguel) throws SQLException {
        if (alDao.open()==null){
            alDao.open();
        }
        alDao.insert(aluguel);
        alDao.close();
    }

    @Override
    public void update(Aluguel aluguel) throws SQLException {
        if (alDao.open()==null){
            alDao.open();
        }
        alDao.update(aluguel);
        alDao.close();
    }

    @Override
    public void delete(Aluguel aluguel) throws SQLException {
        if (alDao.open()==null){
            alDao.open();
        }
        alDao.delete(aluguel);
        alDao.close();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Aluguel findOne(Aluguel aluguel) throws SQLException {
        if (alDao.open()==null){
            alDao.open();
        }
        return alDao.findOne(aluguel);
    }

    @Override
    public List<Aluguel> findAll() throws SQLException {
        if (alDao.open()==null){
            alDao.open();
        }
        return alDao.findAll();
    }
}
