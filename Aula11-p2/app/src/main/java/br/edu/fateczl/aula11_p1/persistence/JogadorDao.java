package br.edu.fateczl.aula11_p1.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.aula11_p1.model.Jogador;
import br.edu.fateczl.aula11_p1.model.Time;

public class JogadorDao implements ICRUDDao<Jogador>, IJogadorDao{

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;


    public JogadorDao(Context context){
        this.context = context;
    }

    private ContentValues getContentValues(Jogador jogador){
        ContentValues content = new ContentValues();
        content.put("id", jogador.getId());
        content.put("nome", jogador.getNome());
        content.put("data_nasc", jogador.getDataNasc());
        content.put("altura", jogador.getAltura());
        content.put("peso", jogador.getPeso());
        content.put("TimeCodigo", jogador.getTime().getCodigo());
        return content;

    }

    @Override
    public void inserir(Jogador jogador) throws SQLException, ClassNotFoundException {
        open();
        ContentValues content = getContentValues(jogador);
        db.insert("Jogador", null,content);
        close();
    }

    @Override
    public void atualizar(Jogador jogador) throws SQLException, ClassNotFoundException {
        open();
        ContentValues content = getContentValues(jogador);
        db.update("Jogador", content, "id = " + jogador.getId(), null);
        close();
    }

    @Override
    public void excluir(Jogador jogador) throws SQLException, ClassNotFoundException {
        open();
        ContentValues content = getContentValues(jogador);
        db.delete("Jogador", "id = " + jogador.getId(), null);
        close();
    }

    @SuppressLint("Range")
    @Override
    public Jogador buscar(Jogador jogador) throws SQLException, ClassNotFoundException {
        open();
        String sql = "SELECT j.id, j.nome, j.data_nasc, j.altura, j.peso," +
                " t.codigo AS timeCodigo, t.nome_time AS timeNome," +
                " t.cidade AS timeCidade from jogador j, time t" +
                " WHERE j.TimeCodigo = t.codigo AND " +
                "j.id = " + jogador.getId();

       Cursor cursor =  db.rawQuery(sql, null);

       if (cursor != null){
           cursor.moveToFirst();
       }

        if (!cursor.isAfterLast()){
            Time time = new Time();
            time.setCodigo(cursor.getInt(cursor.getColumnIndex("timeCodigo")));
            time.setNome(cursor.getString(cursor.getColumnIndex("timeNome")));
            time.setCidade(cursor.getString(cursor.getColumnIndex("timeCidade")));

            jogador.setId(cursor.getInt(cursor.getColumnIndex("id")));
            jogador.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            jogador.setDataNasc(cursor.getString(cursor.getColumnIndex("data_nasc")));
            jogador.setAltura(cursor.getDouble(cursor.getColumnIndex("altura")));
            jogador.setPeso(cursor.getDouble(cursor.getColumnIndex("peso")));
            jogador.setTime(time);

            cursor.moveToNext();
        }
        cursor.close();
       return jogador;
    }

    @SuppressLint("Range")
    @Override
    public List<Jogador> listarTodos() throws SQLException, ClassNotFoundException {
        open();
        List<Jogador> listaJogadores = new ArrayList<>();

        String sql = "SELECT j.id, j.nome, j.data_nasc, j.altura, j.peso," +
                " t.codigo AS timeCodigo, t.nome_time AS timeNome," +
                " t.cidade AS timeCidade from jogador j, time t" +
                " WHERE j.TimeCodigo = t.codigo";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()){
            Jogador jogador = new Jogador();
            Time time = new Time();
            time.setCodigo(cursor.getInt(cursor.getColumnIndex("timeCodigo")));
            time.setNome(cursor.getString(cursor.getColumnIndex("timeNome")));
            time.setCidade(cursor.getString(cursor.getColumnIndex("timeCidade")));

            jogador.setId(cursor.getInt(cursor.getColumnIndex("id")));
            jogador.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            jogador.setDataNasc(cursor.getString(cursor.getColumnIndex("data_nasc")));
            jogador.setAltura(cursor.getDouble(cursor.getColumnIndex("altura")));
            jogador.setPeso(cursor.getDouble(cursor.getColumnIndex("peso")));
            jogador.setTime(time);

            listaJogadores.add(jogador);
            cursor.moveToNext();
        }
        cursor.close();
        return listaJogadores;
    }

    @Override
    public JogadorDao open() throws SQLException {
        gDao = new GenericDao(context);
        db = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() throws SQLException {
        gDao.close();
    }
}
