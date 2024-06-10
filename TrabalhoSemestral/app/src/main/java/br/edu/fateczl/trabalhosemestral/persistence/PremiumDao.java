package br.edu.fateczl.trabalhosemestral.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;

import br.edu.fateczl.trabalhosemestral.model.ClientePadrao;
import br.edu.fateczl.trabalhosemestral.model.ClientePremium;
import br.edu.fateczl.trabalhosemestral.model.FormaPagamentoClube;
import br.edu.fateczl.trabalhosemestral.model.PagamentoCredito;
import br.edu.fateczl.trabalhosemestral.model.PagamentoDebitoConta;

public class PremiumDao implements ICRUDDao<ClientePremium>, IPremiumDao{

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public PremiumDao(Context context) {
        this.context = context;
    }

    private static ContentValues getContentValues(ClientePremium c) {
        ContentValues content = new ContentValues();
        content.put("idCliente", c.getCPF());
        content.put("idPagamento", c.getPagamento().getTipo());
        content.put("Stream", c.getStreaming());
        return content;
    }

    @Override
    public void inserir(ClientePremium clientePremium) throws SQLException, ClassNotFoundException {
        open();
        ContentValues content = getContentValues(clientePremium);
        db.insert("Premium", null, content);
        close();
    }

    @Override
    public void atualizar(ClientePremium clientePremium) throws SQLException, ClassNotFoundException {
        open();
        ContentValues content = getContentValues(clientePremium);
        db.update("Premium", content, "idCliente = " + clientePremium.getCPF(), null);
        close();
    }

    @Override
    public void excluir(ClientePremium clientePremium) throws SQLException, ClassNotFoundException {
        open();
        ContentValues content = getContentValues(clientePremium);
        db.delete("Credito", "idPagamento = " + clientePremium.getCPF(), null);
        db.delete("Debito", "idPagamento = " + clientePremium.getCPF(), null);
        db.delete("Pagamento", "Cliente = " + clientePremium.getCPF(), null);
        db.delete("Premium", "idCliente = " + clientePremium.getCPF(), null);
        close();
    }

    @SuppressLint("Range")
    @Override
    public ClientePremium buscar(ClientePremium clientePremium) throws SQLException, ClassNotFoundException {
        open();
        String sql = "SELECT Stream, idPagamento from Premium WHERE idCliente = " + clientePremium.getCPF();

        Cursor cursor =  db.rawQuery(sql, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        if (!cursor.isAfterLast()){
            clientePremium.setStreaming(cursor.getString(cursor.getColumnIndex("Stream")));
            if (cursor.getColumnIndex("idPagamento") == 1){
                PagamentoCredito p = new PagamentoCredito();
                p.setTipo(cursor.getString(cursor.getColumnIndex("idPagamento")));
            } else {
                PagamentoDebitoConta p = new PagamentoDebitoConta();
                p.setTipo(cursor.getString(cursor.getColumnIndex("idPagamento")));
            }

        }
        cursor.close();
        return clientePremium;
    }

    @Override
    public PremiumDao open() throws SQLException {
        gDao = new GenericDao(context);
        db = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() throws SQLException {
        gDao.close();
    }
}
