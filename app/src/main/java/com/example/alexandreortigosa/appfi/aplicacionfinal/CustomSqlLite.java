package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by alexandreortigosa on 2/7/15.
 */
public class CustomSqlLite extends SQLiteOpenHelper{

    String sqlCreate = "CREATE TABLE IF NOT EXISTS Usuarios (codigo INTEGER, nombre TEXT, password TEXT)";
    String sqlCreateRanking = "CREATE TABLE IF NOT EXISTS Ranking (nombre TEXT, intentos INTEGER)";

    public CustomSqlLite(Context contexto, String nombre,
                                CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
        db.execSQL(sqlCreateRanking);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL("DROP TABLE IF EXISTS Ranking");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
        db.execSQL(sqlCreateRanking);

    }
}
