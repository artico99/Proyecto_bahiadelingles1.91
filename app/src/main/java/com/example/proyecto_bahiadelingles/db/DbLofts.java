package com.example.proyecto_bahiadelingles.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyecto_bahiadelingles.model.Loft;

import java.util.ArrayList;

public class DbLofts extends DbHelper{

    Context context;

    public DbLofts(@Nullable Context context) {
        super(context);

        this.context = context;

    }

    public long insertarLoft(String nombre, String comentario)
    {
        long id = 0;
        try
        {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("comentario", comentario);

            id = db.insert(TABLA_LOFTS, null, values);
        }
        catch(Exception ex)
        {
            ex.toString();
        }
        return id;

    }

    //MOSTRAR DATOS
    public ArrayList<Loft> mostrarLofts()
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Loft> listaLoft = new ArrayList<>();
        Loft loft = null;
        Cursor cursorLoft = null;

        cursorLoft = db.rawQuery("SELECT * FROM " + TABLA_LOFTS, null);

        if(cursorLoft.moveToFirst())
        {
            do{
                    loft = new Loft();
                    loft.setId(cursorLoft.getInt(0));
                    loft.setNombre(cursorLoft.getString(1));
                    loft.setComentario(cursorLoft.getString(2));
                    listaLoft.add(loft);
                } while (cursorLoft.moveToNext());
        }
        cursorLoft.close();

        return listaLoft;
    }

    public Loft seleccionarLoft(int id)
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Loft loft = null;
        Cursor cursorLoft;

        cursorLoft = db.rawQuery("SELECT * FROM " + TABLA_LOFTS + " WHERE id = " + id + " LIMIT 1 ", null);

        if(cursorLoft.moveToFirst())
        {
            loft = new Loft();
            loft.setId(cursorLoft.getInt(0));
            loft.setNombre(cursorLoft.getString(1));
            loft.setComentario(cursorLoft.getString(2));

        }
        cursorLoft.close();

        return loft;
    }
    public Loft seleccionarLoftNombre(String nombre)
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Loft loft = null;
        Cursor cursorLoft;

        cursorLoft = db.rawQuery("SELECT * FROM " + TABLA_LOFTS + " WHERE nombre = " + nombre + " LIMIT 1 ", null);

        if(cursorLoft.moveToFirst())
        {
            loft = new Loft();
            loft.setId(cursorLoft.getInt(0));
            loft.setNombre(cursorLoft.getString(1));
            loft.setComentario(cursorLoft.getString(2));

        }
        cursorLoft.close();

        return loft;
    }

    public boolean editarLoft(int id, String nombre, String comentario)
    {
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Loft loft;
        loft = new Loft();
        String ida;

        try
        {

            db.execSQL(" UPDATE " + TABLA_LOFTS + " SET nombre = '" + nombre + "', comentario = '" + comentario + "' WHERE id = '" + id + "' ");
        }
        catch(Exception ex)
        {
            ex.toString();
            correcto = false;
        }
        finally
        {
            db.close();
        }
        return correcto;
    }
    public boolean editarLoftcliente(String id, String comentario)
    {
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try
        {

            db.execSQL(" UPDATE " + TABLA_LOFTS + " SET comentario = '" + comentario + "' WHERE nombre = '" + id + "' ");
        }
        catch(Exception ex)
        {
            ex.toString();
            correcto = false;
        }
        finally
        {
            db.close();
        }
        return correcto;
    }
    public boolean eliminarLoft(int id)
    {
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try
        {

            db.execSQL(" DELETE FROM " + TABLA_LOFTS + " WHERE id = '" + id + "' ");
            correcto = true;
        }
        catch(Exception ex)
        {
            ex.toString();
            correcto = false;
        }
        finally
        {
            db.close();
        }
        return correcto;
    }

}
