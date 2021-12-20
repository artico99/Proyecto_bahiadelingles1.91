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

    public long insertarLoft(String num,String nombre, String estado)
    {
        long id = 0;
        try
        {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Numero",num);
            values.put("nombre", nombre);
            values.put("estado", estado);


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
                    loft.setNum(cursorLoft.getString(1));
                    loft.setNombre(cursorLoft.getString(2));
                    loft.setEstado(cursorLoft.getString(7));
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
            loft.setNum(cursorLoft.getString(1));
            loft.setNombre(cursorLoft.getString(2));
            loft.setLuz(cursorLoft.getInt(3));
            loft.setAgua(cursorLoft.getInt(4));
            loft.setGas(cursorLoft.getInt(5));
            loft.setReservado(cursorLoft.getInt(6));
            loft.setEstado(cursorLoft.getString(7));

        }
        cursorLoft.close();

        return loft;
    }
    public Loft seleccionarLoftNumero(String numero)
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Loft loft = null;
        Cursor cursorLoft;

        cursorLoft = db.rawQuery("SELECT * FROM " + TABLA_LOFTS + " WHERE numero = " + numero + " LIMIT 1 ", null);

        if(cursorLoft.moveToFirst())
        {
            loft = new Loft();
            loft.setId(cursorLoft.getInt(0));
            loft.setNum(cursorLoft.getString(1));
            loft.setNombre(cursorLoft.getString(2));
            loft.setLuz(cursorLoft.getInt(3));
            loft.setAgua(cursorLoft.getInt(4));
            loft.setGas(cursorLoft.getInt(5));
            loft.setEstado(cursorLoft.getString(7));

        }
        cursorLoft.close();

        return loft;
    }

    public boolean editarLoft(int id, String numero, String nombre,  String estado ,int luz, int Agua, int Gas, int reservado)
    {
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Loft loft;
        loft = new Loft();
        String ida;

        try
        {

            db.execSQL(" UPDATE " + TABLA_LOFTS + " SET Numero = '" + numero + "', nombre = '" + nombre + "',  Luz =  '" + luz + "', Agua = '"+ Agua +"', Gas = '" + Gas + "', Reservado = '" + reservado + "', estado = '" + estado + "' WHERE id = '" + id + "' ");
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
    public boolean editarLoftcliente(String id, String estado,int luz, int Agua, int Gas)
    {
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try
        {

            db.execSQL(" UPDATE " + TABLA_LOFTS + " SET estado = '" + estado + "', Luz =  '" + luz + "', Agua = '"+ Agua +"', Gas = '" + Gas + "' WHERE numero = '" + id + "' ");
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
