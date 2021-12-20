package com.example.proyecto_bahiadelingles.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NOMBRE = "proyecto_bahia_db";
    public static final String TABLA_LOFTS ="lofts";
    public static final String TABLA_CLIENTE ="cliente";
    public static  final String TABLA_TRABAJADOR ="trabajador";
    public static  final String TABLA_ADMINISTRACION ="administracion";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLA_LOFTS + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Numero TEXT NOT NULL," +
                "nombre TEXT," +
                "Luz INTEGER," +
                "Agua INTEGER," +
                "Gas INTEGER," +
                "Reservado INTEGER," +
                "estado TEXT )");

        db.execSQL("CREATE TABLE " + TABLA_CLIENTE + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL," +
                "apellido TEXT NOT NULL," +
                "rut TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "correo TEXT NOT NULL," +
                "numeroLoft TEXT NOT NULL," +
                "comentario TEXT )");

        db.execSQL("CREATE TABLE " + TABLA_TRABAJADOR + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL," +
                "apellido TEXT NOT NULL," +
                "rut TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "correo TEXT NOT NULL," +
                "comentario TEXT )");

        db.execSQL("CREATE TABLE " + TABLA_ADMINISTRACION + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "usuario TEXT NOT NULL," +
                "contraseña TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        db.execSQL("DROP TABLE " + TABLA_ADMINISTRACION);
        onCreate(db);
    }
}
