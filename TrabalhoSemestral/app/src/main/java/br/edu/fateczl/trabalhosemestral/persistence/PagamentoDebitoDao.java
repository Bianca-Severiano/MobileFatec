package br.edu.fateczl.trabalhosemestral.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

import br.edu.fateczl.trabalhosemestral.model.PagamentoCredito;
import br.edu.fateczl.trabalhosemestral.model.PagamentoDebitoConta;

public class PagamentoDebitoDao implements  ICRUDDao<PagamentoDebitoConta>, IPagamentoDebitoDao {

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public PagamentoDebitoDao(Context context) {
        this.context = context;
    }

    private static ContentValues getContentValues(PagamentoDebitoConta dc) {
        ContentValues content = new ContentValues();
        content.put("idPagamento", dc.getNomeTitular());
        content.put("conta", dc.getConta());
        content.put("agencia", dc.getAgencia());
        content.put("banco", dc.getBanco());
        return content;
    }

    private static ContentValues getContentValuesPagamento(PagamentoDebitoConta dc) {
        ContentValues content = new ContentValues();
        content.put("idTipo", dc.getTipo());
        content.put("Cliente", dc.getNomeTitular());
        return content;
    }

    @Override
    public void inserir(PagamentoDebitoConta pagamentoDebitoConta) throws SQLException, ClassNotFoundException {
        open();
        ContentValues content2 = getContentValues(pagamentoDebitoConta);
        ContentValues content = getContentValuesPagamento(pagamentoDebitoConta);
        db.insert("Pagamento", null, content);
        db.insert("Debito", null, content2);
        close();
    }

    @Override
    public void atualizar(PagamentoDebitoConta pagamentoDebitoConta) throws SQLException, ClassNotFoundException {
        open();
        ContentValues content2 = getContentValues(pagamentoDebitoConta);
        ContentValues content = getContentValuesPagamento(pagamentoDebitoConta);
        db.update("Pagamento", content, "Cliente = " + pagamentoDebitoConta.getNomeTitular(), null);
        db.update("Debito", content2, "idPagamento = " + pagamentoDebitoConta.getNomeTitular(), null);
        close();
    }

    @Override
    public void excluir(PagamentoDebitoConta pagamentoDebitoConta) throws SQLException, ClassNotFoundException {
        open();
        ContentValues content2 = getContentValues(pagamentoDebitoConta);
        ContentValues content = getContentValuesPagamento(pagamentoDebitoConta);
        db.delete("Pagamento", "Cliente = " + pagamentoDebitoConta.getNomeTitular(), null);
        db.delete("Debito", "idPagamento = " + pagamentoDebitoConta.getNomeTitular(), null);
        close();
    }

    @SuppressLint("Range")
    @Override
    public PagamentoDebitoConta buscar(PagamentoDebitoConta pagamentoDebitoConta) throws SQLException, ClassNotFoundException {
        open();
        String sql = "SELECT p.idTipo as TipoPagamento, p.Cliente as CPFCliente," +
                " c.conta AS Conta, c.banco AS banco," +
                " c.agencia AS agencia, cl.Nome as Nome from Pagamento p, Credito c, Cliente cl" +
                " WHERE p.idTipo = c.idPagamento AND " +
                "c.idPagamento = cl.CPF  AND " +
                "c.idPagamento " + pagamentoDebitoConta.getNomeTitular();

        Cursor cursor =  db.rawQuery(sql, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        if (!cursor.isAfterLast()){
            pagamentoDebitoConta.setNomeTitular(cursor.getString(cursor.getColumnIndex("CPFCliente")));
            pagamentoDebitoConta.setTipo(cursor.getString(cursor.getColumnIndex("TipoPagamento")));
            pagamentoDebitoConta.setConta(cursor.getInt(cursor.getColumnIndex("Conta")));
            pagamentoDebitoConta.setAgencia(cursor.getInt(cursor.getColumnIndex("agencia")));
            pagamentoDebitoConta.setBanco(cursor.getString(cursor.getColumnIndex("banco")));
            cursor.moveToNext();
        }
        cursor.close();
        return pagamentoDebitoConta;
    }

    @Override
    public PagamentoDebitoDao open() throws SQLException {
        gDao = new GenericDao(context);
        db = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() throws SQLException {
        gDao.close();
    }
}
