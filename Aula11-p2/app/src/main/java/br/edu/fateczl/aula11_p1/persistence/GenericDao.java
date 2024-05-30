package br.edu.fateczl.aula11_p1.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDao extends SQLiteOpenHelper {

    private static final String DATABASE = "FutebolMobile";
    private static final int DATABASE_VER = 1;

    private static final String CREATE_TABLE_TIME = "Create table Time ( " +
            "codigo int(10) NOT NULL primary key, " +
            "nome_time varchar(50) NOT NULL, " +
            "cidade varchar(80) NOT NULL" +
            ");";

    private static final String CREATE_TABLE_JOGADOR = "Create table Jogador (" +
            "id int(10) NOT NULL PRIMARY KEY, " +
            "nome varchar(100) NOT NULL, " +
            "data_nasc String NOT NULL, " +
            "altura decimal (4,2) NOT NULL, " +
            "peso decimal (4,1) NOT NULL, " +
            "TimeCodigo int(10), FOREIGN KEY (TimeCodigo) REFERENCES Time (Codigo) " +
            ");";
    public GenericDao(Context context){
        super(context, DATABASE, null, DATABASE_VER);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TIME);
        db.execSQL(CREATE_TABLE_JOGADOR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS time");
            db.execSQL("DROP TABLE IF EXISTS jogador");
            onCreate(db);
        }

    }
}
