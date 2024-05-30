package br.edu.fateczl.aula11_p1.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.aula11_p1.model.Time;

public class TimeDao implements ICRUDDao<Time>, ITimeDao {

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public TimeDao(Context context){
        this.context = context;
    }


    @Override
    public void inserir(Time time) throws SQLException, ClassNotFoundException {
        open();
        ContentValues content = getContentValues(time);
        db.insert("Time", null, content);
        close();
    }

    private static ContentValues getContentValues(Time time) {
        ContentValues content = new ContentValues();
        content.put("codigo", time.getCodigo());
        content.put("nome_time", time.getNome());
        content.put("cidade", time.getCidade());
        return content;
    }

    @Override
    public void atualizar(Time time) throws SQLException, ClassNotFoundException {
        open();
        ContentValues content = getContentValues(time);
        db.update("Time", content, "codigo = " + time.getCodigo(), null);
        close();
    }

    @Override
    public void excluir(Time time) throws SQLException, ClassNotFoundException {
        open();
        ContentValues content = getContentValues(time);
        db.delete("Time", "codigo = " + time.getCodigo(), null);
        close();
    }

    @SuppressLint("Range")
    @Override
    public Time buscar(Time time) throws SQLException, ClassNotFoundException {
        open();
        String sql = "SELECT codigo, nome_time, cidade FROM Time WHERE codigo = " + time.getCodigo();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null){
            cursor.moveToFirst();
        }

        if(!cursor.isAfterLast() ){
            time.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            time.setNome(cursor.getString(cursor.getColumnIndex("nome_time")));
            time.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
        }
        cursor.close();

        return time;
    }

    @SuppressLint("Range")
    @Override
    public List<Time> listarTodos() throws SQLException, ClassNotFoundException {
        open();
        List<Time> listaTimes = new ArrayList<>();

        String sql = "SELECT codigo, nome_time, cidade FROM Time";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null){
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast() ){
            Time time = new Time();
            time.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            time.setNome(cursor.getString(cursor.getColumnIndex("nome_time")));
            time.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));

            listaTimes.add(time);
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return listaTimes;
    }

    @Override
    public TimeDao open() throws SQLException {
        gDao = new GenericDao(context);
        db = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() throws SQLException {
        gDao.close();
    }
}
