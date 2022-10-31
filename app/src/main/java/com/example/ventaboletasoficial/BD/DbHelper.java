package com.example.ventaboletasoficial.BD;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {



    private static final String CREAR_TABLA_USUARIOS = "CREATE TABLE " +
            constantes.TABLA_USUARIO +
            " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " NOMBRE TEXT NOT NULL, " +
            " EMAIL TEXT NOT NULL)";

    public DbHelper(@Nullable Context context) {
        super(context, constantes.NOMBRE_BD, null, constantes.VERSIO_BD);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Creación tabla usuarios
        sqLiteDatabase.execSQL(CREAR_TABLA_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Actualiza de la BD por cambio de version
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+constantes.TABLA_USUARIO);
        sqLiteDatabase.execSQL(CREAR_TABLA_USUARIOS);
    }
}
