package com.example.ventaboletasoficial.BD;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {



    private static final String CREAR_TABLA_USUARIOS = "CREATE TABLE " +
            constantes.TABLA_USUARIOS +
            " (DOCUMENTO INTEGER PRIMARY KEY, " +
            " NOMBRE TEXT NOT NULL, " +
            " TELEFONO TEXT NOT NULL)";

    private static final String CREAR_TABLA_VENTAS = "CREATE TABLE " +
            constantes.TABLA_Ventas +
            " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " ASIENTOS TEXT NOT NULL, " +
            " DOCUMENTOusu INTEGER," +
            " TOTAL INTEGER,"+
            " FOREIGN KEY (DOCUMENTOusu) REFERENCES "+constantes.TABLA_USUARIOS+ " (DOCUMENTO)"+
            ")";

    /*-- Employee definition

CREATE TABLE [Employee]
(
    [EmployeeId] INTEGER  NOT NULL,
    [LastName] NVARCHAR(20)  NOT NULL,
    [FirstName] NVARCHAR(20)  NOT NULL,
    [Title] NVARCHAR(30),
    [ReportsTo] INTEGER,
    [BirthDate] DATETIME,
    [HireDate] DATETIME,
    [Address] NVARCHAR(70),
    [City] NVARCHAR(40),
    [State] NVARCHAR(40),
    [Country] NVARCHAR(40),
    [PostalCode] NVARCHAR(10),
    [Phone] NVARCHAR(24),
    [Fax] NVARCHAR(24),
    [Email] NVARCHAR(60),
    CONSTRAINT [PK_Employee] PRIMARY KEY  ([EmployeeId]),
    FOREIGN KEY ([ReportsTo]) REFERENCES [Employee] ([EmployeeId])
		ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE UNIQUE INDEX [IPK_Employee] ON [Employee]([EmployeeId]);
CREATE INDEX [IFK_EmployeeReportsTo] ON [Employee] ([ReportsTo]);*/

    public DbHelper(@Nullable Context context) {
        super(context, constantes.NOMBRE_BD, null, constantes.VERSIO_BD);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Creación tabla usuarios
        sqLiteDatabase.execSQL(CREAR_TABLA_USUARIOS);
        sqLiteDatabase.execSQL(CREAR_TABLA_VENTAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Actualiza de la BD por cambio de version
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+constantes.TABLA_USUARIOS);
        sqLiteDatabase.execSQL(CREAR_TABLA_USUARIOS);
    }


}
